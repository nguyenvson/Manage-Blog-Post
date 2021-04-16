package lab11.blogs.data;

import lab11.blogs.models.Comment;
import lab11.blogs.models.Post;
import lab11.blogs.models.Tag;
import lab11.blogs.models.User;
import lab11.blogs.services.CommentService;
import lab11.blogs.services.PostService;
import lab11.blogs.services.TagService;
import lab11.blogs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InsertData {
    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    public InsertData() {
    }

    @Transactional
    public void insert() {
        User user1 = new User("username1", "123456789", "Sơn Nguyễn","admin");
        User user2 = new User("username2", "123456789", "Sơn Cô Đơn", "viewer");
        User user3 = new User("username3", "123456789", "Hoàng Đoàng Quàng", "viewer");
        User user4 = new User("username4", "123456789", "Phát Nhút Nhát", "viewer");
        User user5 = new User("username5", "123456789", "Trang Lang Thang", "viewer");
        User user6 = new User("username6", "123456789", "Hường Bình Thường", "viewer");
        User user7 = new User("username7", "123456789", "Nguyên Luyên Thuyên", "viewer");

        Tag tag1 = new Tag("Bài học", 2);
        Tag tag2 = new Tag("Nguồn sống", 2);
        Tag tag3 = new Tag("Trí tuệ", 2);

        List<Tag> tags1 = new ArrayList<>();
        List<Tag> tags2 = new ArrayList<>();
        List<Tag> tags3 = new ArrayList<>();

        tags1.add(tag1);
        tags1.add(tag2);

        tags2.add(tag2);
        tags2.add(tag3);

        tags3.add(tag1);
        tags3.add(tag3);

        String content1 ="Chính trực là đức tính của sự trung thực và sự tuân theo các tiêu chuẩn đạo đức một cách mạnh mẽ; " +
                "nói cách khác, nó là sự trung thực gắn liền với đạo đức. Thông thường, nó là một sự lựa chọn của cá nhân để " +
                "giữ cho người đó luôn bám chắc vào các nguyên tắc và tiêu chuẩn đạo đức.";
        Post post1 = new Post("Chính Trực", content1, LocalDate.of(2020,3,1),
                LocalDate.of(2020,3,1),"private", tags1, user1);

        String content2 ="Trực giác là một quá trình cho phép chúng ta hiểu, biết sự việc một cách trực tiếp mà không cần lý luận," +
                " phân tích hay bắc cầu giữa phần ý thức và phần tiềm thức của tâm trí, hay giữa bản năng và lý trí. " +
                "Ngôn ngữ đời thường hay gọi là \"trực giác mách bảo\", dùng để chỉ việc hành động theo nội tâm, " +
                "nhận thấy những sự việc không hợp lý và dự cảm mà không cần lý do.\n" +
                "Trực giác còn được gọi là linh tính hay giác quan thứ sáu cho phép ta thấy được những gì mà năm giác quan khác " +
                "không thể thấy được, cụ thể như linh cảm, cảm nhận, tưởng tượng... hay tất cả những gì thuộc về thế giới vô hình " +
                "(trong khi năm giác quan còn lại chỉ thấy được những gì ở thế giới hữu hình (tức là những gì đang tồn tại))." +
                " Người ta thường hay nói rằng \"nhiều khi nhờ trực giác mà con người thoát được nhiều hiểm nguy\".";
        Post post2 = new Post("Trực giác", content2, LocalDate.of(2020,5,5),
                LocalDate.of(2020,5,5),"published", tags2, user1);

        String content3 = "Kiên trì là những kỹ năng và thái độ sống của con người để theo đuổi mục tiêu mà mình đã đặt ra " +
                "trong cuộc sống. Đó chính là sự nỗ lực hết mình, cố gắng không ngừng, luôn vững vàng, không bỏ cuộc dù có gặp phải " +
                "những gian nan, thử thách, thậm chí là những thất bại cũng không buông bỏ và quyết tâm làm đến cùng. " +
                "Sự kiên trì là một trong những yếu tố quan trọng nhất góp phần tạo nên sự thành công của mỗi người trong cuộc sống.";
        Post post3 = new Post("Kiên Trì", content3, LocalDate.of(2020,3,1),
                LocalDate.of(2020,3,1),"published", tags2, user1);

        String content4 = "Tư duy là phạm trù triết học dùng để chỉ những hoạt động của tinh thần, đem những cảm giác của người ta" +
                " sửa đổi và cải tạo thế giới thông qua hoạt động vật chất, làm cho người ta có nhận thức đúng đắn về sự vật " +
                "và ứng xử tích cực với nó.\n" +
                "Theo Từ điển Bách khoa toàn thư Việt Nam, tập 4 (Nhà xuất bản Từ điển bách khoa. Hà Nội. 2005); " +
                "Tư duy là sản phẩm cao nhất của vật chất được tổ chức một cách đặc biệt – bộ não con người. " +
                "Tư duy phản ánh tích cực hiện thực khách quan dưới dạng các khái niệm, sự phán đoán, lý luận.v.v...\n" +
                "Theo triết học duy tâm khách quan, tư duy là sản phẩm của \"ý niệm tuyệt đối\" với tư cách là bản năng " +
                "siêu tự nhiên, độc lập, không phụ thuộc vào vật chất. Theo George Wilhemer Fridrick Heghen: \"Ý niệm tuyệt đối " +
                "là bản nguyên của hoạt động và nó chỉ có thể biểu hiện trong tư duy, trong nhận thức tư biện mà thôi\". " +
                "Karl Marx nhận xét: \"Đối với Heghen, vận động của tư duy được ông nhân cách hóa dưới tên gọi \"ý niệm\" " +
                "là chúa sáng tạo ra hiện thực; hiện thực chỉ là hình thức bề ngoài của ý niệm\".\n" +
                "Theo triết học duy vật biện chứng, tư duy là một trong các đặc tính của vật chất phát triển đến trình độ" +
                " tổ chức cao. Về lý thuyết, Karl Marx cho rằng: \"Vận động kiểu tư duy chỉ là sự vận động của hiện thực khách " +
                "quan được di chuyển vào và được cải tạo/tái tạo trong đầu óc con người dưới dạng một sự phản ánh\". Những " +
                "luận cứ này còn dựa trên những nghiên cứu thực nghiệm của Ivan Petrovich Pavlov, nhà sinh lý học, nhà tư tưởng" +
                " người Nga. Bằng các thí nghiệm tâm-sinh lý áp dụng trên động vật và con người, ông đi đến kết luận: \"Hoạt động" +
                " tâm lý là kết quả của hoạt động sinh lý của một bộ phận nhất định của bộ óc\".";
        Post post4 = new Post("Tư Duy", content4, LocalDate.of(2020,10,10),
                LocalDate.of(2020,10,10),"published", tags3, user1);

        Comment comment2 = new Comment("Bài viết cũng được",user2, post1, "Approve");
        Comment comment3 = new Comment("Hay dữ, Like",user3, post2, "Approve");
        Comment comment4 = new Comment("Bài viết cũng được",user4, post3, "Approve");
        Comment comment5 = new Comment("Dữ dữ",user5, post4, "Approve");
        Comment comment6 = new Comment("Viết gì dỡ dữ",user6, post4, "Approve");
        Comment comment1 = new Comment("Bài viết khá tốt",user7, post1, "Approve");

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);

        tagService.save(tag1);
        tagService.save(tag2);
        tagService.save(tag3);

        postService.save(post1);
        postService.save(post2);
        postService.save(post3);
        postService.save(post4);

        commentService.save(comment1);
        commentService.save(comment2);
        commentService.save(comment3);
        commentService.save(comment4);
        commentService.save(comment5);
        commentService.save(comment6);
   }


}
