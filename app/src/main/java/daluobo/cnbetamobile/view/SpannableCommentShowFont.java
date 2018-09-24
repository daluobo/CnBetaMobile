package daluobo.cnbetamobile.view;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

/**
 * Created by daluobo on 2017/9/17.
 */

public class SpannableCommentShowFont {
    public static SpannableString changeFont(String locationStr, String authorStr, String titleStr) {
        SpannableString ss;
        if (authorStr.length() < 5) {
            ss = new SpannableString("对新闻: " + titleStr + " 的评论");
            //设置字体前景色
            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#4c5454")),
                    4,
                    4 + titleStr.length() + 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            ss = new SpannableString("来自 " + locationStr + " " + authorStr + titleStr + " 的评论");
            //设置字体前景色
            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#4c5454")),
                    4 + locationStr.length() + authorStr.length(),
                    4 + locationStr.length() + authorStr.length() + titleStr.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // flag:标识在 Span 范围内的文本前后输入新的字符时是否把它们也应用这个效果
        // Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)、
        // Spanned.SPAN_INCLUSIVE_EXCLUSIVE(前面包括，后面不包括)、
        // Spanned.SPAN_EXCLUSIVE_INCLUSIVE(前面不包括，后面包括)、
        // Spanned.SPAN_INCLUSIVE_INCLUSIVE(前后都包括)

        //设置字体样式正常，粗体，斜体，粗斜体
        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                3,
                4 + locationStr.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体


        return ss;
    }
}
