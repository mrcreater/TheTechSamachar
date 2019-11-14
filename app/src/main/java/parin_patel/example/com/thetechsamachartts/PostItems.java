package parin_patel.example.com.thetechsamachartts;


/**
 * Created by parin on 17/9/17.
 */

public class PostItems {

    private String title;
    //   private String expert;
    private String image;
    //
    //
 //   private String content;
    private String author;
    private String date;


    public PostItems(String title, String author, String image) {
        this.title = title;
        this.image = image;
        //this.expert = expert;
       //   this.content = content;
        this.author = author;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

//    public String getExpert() {
//        return expert;
//    }

    public String getImage() {
        return image;
    }


  //  public String getContent() {
    //     return content;
  //   }

    public String getAuthor() {
        return author;
    }

//    public String getDate() {
//        return date;
//    }

}



