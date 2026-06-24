# My Questions

## 1. `Instant` khác gì `LocalDateTime` (trong `Airline.java`)

Cả hai đều thuộc `java.time`, nhưng khác nhau ở chỗ **có gắn với múi giờ / mốc thời gian tuyệt đối hay không**.

| | `Instant` | `LocalDateTime` |
|---|---|---|
| Bản chất | Một **điểm thời gian tuyệt đối** trên trục thời gian UTC (số giây/nano kể từ epoch `1970-01-01T00:00:00Z`) | Ngày + giờ **không có thông tin múi giờ** (vd `2026-06-23T14:30`) |
| Có timezone? | Có — luôn quy về UTC | Không — chỉ là "giờ trên đồng hồ treo tường" |
| Ý nghĩa | "Thời điểm này xảy ra ở đâu trên thế giới cũng là cùng một lúc" | "14:30" — nhưng 14:30 ở đâu? Không biết |
| Lưu DB | Thường map sang `TIMESTAMP WITH TIME ZONE` / `timestamptz` | Map sang `TIMESTAMP` (không timezone) |

### Vì sao dùng `Instant` cho `createdAt`/`updatedAt` là lựa chọn đúng

1. **Đây là microservices (`airline-core-service`, có cross-service reference).** Các service có thể chạy trên nhiều máy/region/múi giờ khác nhau. `Instant` đảm bảo mọi nơi đều ghi nhận cùng một mốc tuyệt đối, không bị lệch giờ.
2. **Không mơ hồ (no ambiguity).** Với `LocalDateTime`, nếu một bản ghi tạo lúc `2026-06-23T14:30` thì không thể biết đó là giờ Việt Nam hay giờ UTC — và khi so sánh hai bản ghi tạo ở hai server khác múi giờ sẽ sai. `Instant` không có vấn đề này.
3. **DST (daylight saving time).** `LocalDateTime` có thể trùng lặp hoặc nhảy giờ khi đổi giờ mùa. `Instant` miễn nhiễm vì luôn là UTC.
4. **Quy tắc chuẩn cho audit field:** Lưu `Instant` (UTC) trong DB → chỉ chuyển sang giờ địa phương ở **tầng hiển thị** (frontend/API response). Đây là best practice "store in UTC, display in local".

> Tóm lại: `createdAt`/`updatedAt` là dấu thời gian hệ thống (audit), cần chính xác tuyệt đối và độc lập múi giờ → `Instant`. Còn `LocalDateTime` chỉ hợp khi giờ vốn dĩ không gắn timezone, vd "giờ khởi hành theo giờ địa phương sân bay" hiển thị cho người dùng.

Lưu ý thêm: hai annotation `@CreatedDate` / `@LastModifiedDate` (Spring Data) chỉ tự động điền giá trị khi bật JPA Auditing (`@EnableJpaAuditing` + `@EntityListeners(AuditingEntityListener.class)`). Cần kiểm tra cấu hình đó đã có chưa, nếu không hai trường này sẽ null khi insert.

## 2. Vì sao `Support` dùng `@Embedded`

```java
@Embeddable
public class Support {
  private String email;
  private String phone;
  private String hours;
}
```

`Support` **không phải một entity riêng** (không có `@Id`, không có bảng riêng). Nó là một **value object** — một nhóm field đi chung nhau về mặt nghiệp vụ (thông tin hỗ trợ khách hàng: email, phone, hours).

- `@Embeddable` (đặt trên class `Support`): khai báo "đây là một khối field có thể nhúng vào entity khác".
- `@Embedded` (đặt trên field `support` trong `Airline`): yêu cầu JPA **trải phẳng (flatten)** các field của `Support` thành các cột ngay trong bảng `airlines`.

### Kết quả ở tầng database

Không tạo bảng `support` riêng. Thay vào đó bảng `airlines` có thêm các cột:

```
airlines (
  id, iata_code, icao_code, name, ...,
  email,   -- từ Support
  phone,   -- từ Support
  hours    -- từ Support
)
```

### Vì sao dùng cách này thay vì để 3 field rời trong `Airline`?

1. **Nhóm logic & tái sử dụng (DRY).** `Support` nằm trong `common-lib` → nhiều service/entity khác cũng có thể nhúng cùng khối thông tin hỗ trợ này mà không lặp lại code.
2. **Đúng mô hình DDD.** Đây là quan hệ "thuộc về" (composition), không phải quan hệ thực thể độc lập → value object embeddable là biểu diễn chính xác, không cần khóa ngoại hay JOIN.
3. **Code gọn, đọc dễ:** `airline.getSupport().getEmail()` rõ ràng hơn là rải `supportEmail`, `supportPhone`... trong `Airline`.

> Quy tắc nhận biết: nếu một nhóm thuộc tính **không có định danh riêng** và **vòng đời gắn chặt với entity cha** → dùng `@Embeddable`/`@Embedded`. Nếu nó có ID, được truy vấn/tham chiếu độc lập → mới tách thành `@Entity` với quan hệ `@OneToOne`/`@ManyToOne`.

Một lưu ý nhỏ: nếu sau này có nhiều entity cùng nhúng `Support` và muốn đổi tên cột riêng cho từng nơi, có thể dùng `@AttributeOverrides` tại chỗ `@Embedded`.

## 3. Giải thích chi tiết `getLowestFarePerFlight()` (trong `FareServiceImpl.java`)

### Mục đích nghiệp vụ

Phương thức trả về **giá vé thấp nhất của mỗi chuyến bay** cho một hạng ghế (cabin class) cụ thể.

Đầu vào:
- `flightIds`: danh sách ID các chuyến bay cần tra cứu.
- `cabinClassId`: hạng ghế (vd: Economy, Business...).

Đầu ra: `Map<Long, FareResponse>` — key là `flightId`, value là vé **rẻ nhất** của chuyến bay đó.

Tình huống dùng thực tế: màn hình kết quả tìm chuyến bay, mỗi chuyến hiển thị "giá từ X đ" — chính là vé thấp nhất của chuyến đó.

### Đọc code từng bước

```java
@Override
public Map<Long, FareResponse> getLowestFarePerFlight(List<Long> flightIds, Long cabinClassId) throws Exception {
  // 1. Validate đầu vào (fail-fast tại biên hệ thống)
  if (flightIds == null || flightIds.isEmpty()) {
    throw new Exception("Flight IDs cannot be null or empty");
  }
  if (cabinClassId == null) {
    throw new Exception("Cabin class ID cannot be null");
  }

  // 2. Lấy TẤT CẢ vé của các chuyến bay trong list + đúng hạng ghế (1 query duy nhất)
  List<Fare> fares = fareRepository.findByFlightIdInAndCabinClassId(flightIds, cabinClassId);

  // 3. Gom nhóm theo flightId, mỗi nhóm giữ lại vé có totalPrice nhỏ nhất
  Map<Long, Fare> lowestFares = fares.stream()
      .collect(Collectors.toMap(
          Fare::getFlightId,                 // keyMapper: nhóm theo flightId
          fare -> fare,                       // valueMapper: giá trị ban đầu là chính Fare đó
          (existing, candidate) ->            // mergeFunction: xử lý khi trùng key
              candidate.getTotalPrice() < existing.getTotalPrice()
                  ? candidate
                  : existing));

  // 4. Chuyển từ Map<Long, Fare> (entity) -> Map<Long, FareResponse> (DTO trả ra ngoài)
  return lowestFares.entrySet().stream()
      .collect(Collectors.toMap(
          Map.Entry::getKey,
          entry -> FareMapper.toResponse(entry.getValue())));
}
```

#### Bước 1 — Validate đầu vào

Theo nguyên tắc "validate at system boundaries": kiểm tra `null`/rỗng trước khi xử lý, **fail-fast** với thông điệp rõ ràng thay vì để lỗi mơ hồ phát sinh sâu bên dưới (vd query rỗng, NPE).

#### Bước 2 — Một query duy nhất, tránh N+1

`findByFlightIdInAndCabinClassId` dùng mệnh đề SQL `IN (...)` để lấy **một lần** toàn bộ vé của tất cả chuyến bay. Đây là điểm quan trọng về hiệu năng:

- ✅ **Đúng:** 1 query cho N chuyến bay.
- ❌ **Sai (N+1):** lặp từng `flightId` rồi gọi DB mỗi vòng → N query.

#### Bước 3 — "Trái tim" của thuật toán: `Collectors.toMap` với merge function

Đây là phần dễ nhầm nhất. `Collectors.toMap` có 3 tham số:

| Tham số | Vai trò | Ở đây |
|---|---|---|
| `keyMapper` | Lấy key của map | `Fare::getFlightId` — nhóm theo chuyến bay |
| `valueMapper` | Lấy value khởi tạo | `fare -> fare` — giữ nguyên đối tượng `Fare` |
| `mergeFunction` | Gọi **khi hai phần tử cùng key** | giữ lại vé có `totalPrice` nhỏ hơn |

**Vì sao bắt buộc phải có merge function?** Vì một chuyến bay có thể có **nhiều vé** cùng hạng ghế (vd nhiều fare khác nhau: khuyến mãi, linh hoạt, tiêu chuẩn...). Nếu chỉ truyền 2 tham số, khi gặp key trùng `toMap` sẽ ném `IllegalStateException: Duplicate key`. Merge function chính là logic "khi trùng chuyến bay thì chọn vé rẻ hơn".

Cách hoạt động của merge: stream duyệt từng `Fare`; lần đầu gặp một `flightId` thì đưa thẳng vào map; lần sau gặp lại `flightId` đó, nó gọi `mergeFunction(existing, candidate)` để quyết định giữ vé nào — ở đây so sánh `totalPrice` và giữ cái nhỏ hơn.

Kết quả: `Map<Long, Fare>` — mỗi chuyến bay đúng 1 vé rẻ nhất.

#### Bước 4 — Map entity sang DTO

Không trả `Fare` (entity nội bộ) ra ngoài. Pipeline thứ hai chuyển mỗi entry thành `FareResponse` qua `FareMapper.toResponse`. Đây là quy tắc "map ở biên service/controller", tránh lộ chi tiết tầng persistence ra API.

### Một vài điểm có thể cải thiện (nếu muốn nâng cấp)

1. **Kiểu của price:** so sánh bằng `<` cho thấy `totalPrice` đang là kiểu nguyên thủy (vd `double`/`long`). Với tiền tệ, `BigDecimal` an toàn hơn (tránh sai số dấu phẩy động) — khi đó so sánh dùng `candidate.getTotalPrice().compareTo(existing.getTotalPrice()) < 0`.
2. **Exception cụ thể:** ném `new Exception(...)` (checked, quá chung) nên đổi thành exception nghiệp vụ kế thừa `RuntimeException` (vd `InvalidFareRequestException`) — đúng với coding style Java của dự án.
3. **Chuyến bay không có vé:** nếu một `flightId` trong input không có vé nào khớp, nó sẽ **không xuất hiện** trong map kết quả (chứ không phải value null). Phía gọi cần biết điều này để xử lý hiển thị "hết vé"/"không có giá".
4. **Tie-break giá bằng nhau:** khi hai vé cùng `totalPrice`, merge giữ `existing` (vé gặp trước). Nếu nghiệp vụ cần ưu tiên tiêu chí khác (vd vé linh hoạt hơn), cần bổ sung logic so sánh phụ.

> Tóm lại: hàm này = (1) validate → (2) 1 query gom toàn bộ vé → (3) `toMap` + merge function để chọn vé rẻ nhất mỗi chuyến → (4) map sang DTO. Mấu chốt cần nhớ là **merge function của `Collectors.toMap`**: nó vừa giải quyết key trùng, vừa là nơi cài đặt logic "chọn giá thấp nhất".
