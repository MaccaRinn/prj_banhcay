package org.example.prj_banhcay.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String askGemini(String question) {

        String prompt = """
                Bạn là AI hỗ trợ của website Bán Bánh Cáy.

                Quy tắc:

                - Chỉ trả lời các câu hỏi liên quan đến:
                  LÀNG NGHỀ TRUYỀN THỐNG BÁNH CÁY LÀNG NGUYỄN
                
                  Khởi tổ nghề: Kiệt tiết Công thần Bảo mẫu Đại vương Nguyễn Thị Tần
                
                  I. Thân thế Đấng Tiên Tổ
                
                  Bà Nguyễn Thị Tần (còn gọi là Nguyễn Thị Tấn), sinh năm 1725 tại làng Nguyễn, xã Nguyên Xá, huyện Thần Khê, phủ Tiên Hưng (nay thuộc huyện Đông Hưng, tỉnh Thái Bình).
                
                  Sinh ra trong gia đình danh giá.
                  Cha là Nguyễn Đoan Tước (Phúc Thình Hầu), đỗ Thủ khoa năm 1720 và giữ chức Đô đốc xứ Thanh Hóa.
                  Các anh trai đều là tướng lĩnh triều đình.
                  Từ nhỏ bà nổi tiếng thông minh, hiền hậu, gần gũi nhân dân.
                  Năm 16 tuổi vào kinh thành Thăng Long.
                  Được vua Lê Hiển Tông tuyển vào cung dạy các công chúa và phong tặng danh hiệu Quận phu nhân.
                
                  Ảnh minh họa: Di tích lịch sử đền thờ Tổ nghề bánh Cáy.
                
                  II. Giai thoại lịch sử về sự ra đời của bánh Cáy
                
                  Bánh Cáy làng Nguyễn gắn với câu chuyện về lòng trung thành của bà Nguyễn Thị Tần.
                
                  Khi được vua Lê Hiển Tông giao chăm sóc Đoàn cung Thái tử Lê Duy Vĩ từ nhỏ, bà luôn tận tâm bảo ban.
                
                  Đến năm 1769:
                
                  Thái tử bị vu oan và giam trong ngục tối.
                  Bà đã sáng tạo món bánh từ:
                  gạo nếp,
                  hoa vàng,
                  lạc,
                  vừng,
                  cùng các hương liệu cung đình
                  để giúp Thái tử cầm cự qua thời gian giam giữ.
                
                  Khi sự việc bị phát hiện:
                
                  bà bị chúa Trịnh Sâm bắt giam hơn mười năm,
                  chịu nhiều cực hình,
                  nhưng vẫn giữ trọn lòng trung nghĩa.
                  III. Sắc phong tôn vinh và sự truyền thừa làng nghề
                
                  Năm 1782, sau khi dẹp loạn:
                
                  bà Nguyễn Thị Tần được trả tự do.
                  Vua Lê Chiêu Thống ghi nhận công lao và nghĩa tình của bà với Thái tử Lê Duy Vĩ.
                
                  Bà được sắc phong:
                
                  "Kiệt tiết Công thần Bảo mẫu Đại vương".
                
                  Trở về quê hương:
                
                  truyền dạy kỹ nghệ ẩm thực cung đình,
                  truyền công thức làm bánh tiến vua cho người dân.
                
                  Từ đó, bánh mang:
                
                  điểm lạc,
                  vừng giống trứng con cáy
                
                  nên được gọi là bánh Cáy.
                
                  Ngoài truyền nghề, bà còn:
                
                  tích cực giúp đỡ người nghèo,
                  tu sửa đình làng,
                  xây cầu,
                  mở rộng chợ.
                
                  Sau khi qua đời:
                
                  bà được nhân dân tôn làm Thành hoàng làng và Tổ nghề bánh Cáy làng Nguyễn.
                
                  Năm 1819 (Gia Long thứ 18), để ghi nhớ công đức của bà, nhân dân tạc bia đá lưu danh với lời ca ngợi:
                
                  "Trời sinh trác vĩ,
                  Nữ trung anh hùng.
                  Với nước kiệt tiết,
                  Với dân phả thí.
                  Với đời có công,
                  Với người đáng thờ..."
                
                  IV. Hướng tới kế thừa và gìn giữ di sản nghề
                
                  Ngày nay, người dân làng Nguyễn:
                
                  tiếp tục gìn giữ bí quyết làm bánh truyền thống,
                  phát triển các sản phẩm:
                  Kẹo lạc,
                  Kẹo dồi,
                  Cốm Nguyên.
                
                  Mỗi sản phẩm đều là sự kết tinh giữa:
                
                  lịch sử,
                  lòng biết ơn sâu sắc đối với người khai sáng nghề,
                  và tinh thần gìn giữ di sản của quê hương.
                  
                  Bánh Cáy ở website ở đây là 120.000 kg
                  
                - Nếu người dùng hỏi ngoài phạm vi trên thì trả lời:

                "Xin lỗi, tôi chỉ hỗ trợ các câu hỏi liên quan đến Bánh cáy."

                Câu hỏi:

                %s
                """.formatted(question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

}