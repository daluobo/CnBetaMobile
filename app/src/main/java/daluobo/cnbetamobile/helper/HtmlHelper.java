package daluobo.cnbetamobile.helper;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import daluobo.cnbetamobile.data.local.Article;
import daluobo.cnbetamobile.data.local.Comment;

/**
 * Created by daluobo on 2016/10/24.
 */

public class HtmlHelper {
    private static final String TAG = "HtmlHelper";
    private static final String ASSETS_DIR = "file:///android_asset/";
    private static final String CSS = ASSETS_DIR + "style.css";
    private static final String JS = ASSETS_DIR + "content.js";

    public static String wrapContent(String content) {
        return "<!DOCTYPE html><html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, user-scalable=no\" >" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + CSS + "\"/>" +
                "</head><body id=\"main\"><div class=\"content\">" + content + "<div><script src=\"" + JS + "\"></script>" + "</body></html>";
    }

    public static List<Article> getArticleList(String html) {
        List<Article> data = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Elements list = doc.select(".info_list > li");
        for (Element e : list) {
            if (e.childNodeSize() != 0) {
                Article li = new Article();
                li.link = e.select("p.txt_thumb > a").attr("href");
                li.thumb_img = e.select("p.txt_thumb > a > img").attr("src");

                Element p = e.select("p.txt_detail").first();
                if (p == null) {
                    continue;
                }

                li.title = p.text();
                if (!p.select("span").isEmpty()) {
                    try {
                        li.isHot = true;
                        String colorStr = p.select("span").attr("style");
                        int startIndex = colorStr.indexOf("color:");
                        li.color = colorStr.substring(startIndex + 6, startIndex + 13);

                        Log.d(TAG, "标题颜色：" + li.color);
                    } catch (Exception ex) {
                        Log.d(TAG, "获取标题颜色出错");
                    }
                }

                li.post_time = e.select("p.txt_time > i").first().text();
                li.view = e.select("p.txt_time > i").get(1).text();


                li.id = Integer.parseInt(li.link.substring(li.link.lastIndexOf("/") + 1, li.link.lastIndexOf(".")));

                data.add(li);
            }
        }
        Log.d(TAG, "1-----获取文章列表");
        return data;
    }

    public static List<Comment> getCommentShow(String html) {
        List<Comment> data = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Elements list = doc.select("li.module_hot_cmt");
        for (Element e : list) {
            if (e.childNodeSize() != 0) {
                Comment comment = new Comment();
                Elements ea = e.select("a");

                comment.content = ea.first().text();
                comment.article = ea.get(1).text();
                comment.link = ea.attr("href");
                comment.articleId = Integer.parseInt(comment.link.substring(comment.link.lastIndexOf("/") + 1, comment.link.lastIndexOf(".")));

                comment.location = e.select("strong").text();

                Elements es = e.select("div.jh_footer.jh_text");
                comment.author = es.first().childNode(2).toString();

                data.add(comment);
            }
        }

        Log.d(TAG, "2-----获取精彩评论列表");

        return data;
    }

    public static List<Comment> getComment(String html) {
        List<Comment> data = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements eCount = doc.select("span.morComment > b");
//        resultModel.totalCount = Integer.parseInt(eCount.get(0).text());
//        resultModel.showCount = Integer.parseInt(eCount.get(1).text());

        Elements ec = doc.select("div.comContent");
        for (Element c : ec) {
            Comment comment = new Comment();
            comment.userName = c.select("span.userName").text();
            comment.time = c.select("span.time").text();

            Elements con = c.select("div.con");
            Elements comName = c.select("div.box > span");
            Elements comContent = c.select("div.box > p");

            StringBuffer sb = new StringBuffer();
            if (comName.size() > 0) {
                for (int i = 0; i < comName.size(); i++) {
                    sb.append(comName.get(i).text() + " : " + comContent.get(i).text());
                    if (i != comName.size() - 1) {
                        sb.append("\n\n");
                    }
                }
            }
            comment.conBox = sb.toString();
            comment.content = c.select("div.con > p").first().text();

            comment.commentId = Integer.parseInt(con.attr("id").replaceAll("hotcon", ""));

            comment.up = c.select("b.up  > span").text();
            comment.down = c.select("b.down  > span").text();

            data.add(comment);
        }

        Log.d(TAG, "4-----获取文章评论");
        return data;
    }

    public static Article getArticle(String html) {
        Article data = new Article();
        Document doc = Jsoup.parse(html);

        String idStr = doc.select("article.article-holder").attr("id");
        try {
            data.id = Integer.parseInt(idStr.replaceAll("content_", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        data.title = doc.select("h1.article-tit").text();
        data.source = doc.select("p.article-byline > span").text();
        data.time = doc.select("time.time").text();

        Elements content = doc.select("div.articleCont");
        Elements summ = doc.select("div.article-summ > p");
        data.summ = summ.text();
        data.content = wrapContent(summ.outerHtml() + content.outerHtml());

        Log.d(TAG, "3-----获取文章内容");

        return data;
    }

}
