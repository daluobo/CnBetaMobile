package daluobo.cnbetamobile.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;

/**
 * Created by daluobo on 2016/10/20.
 */
@Keep
@Entity(tableName = "article")
public class Article implements Parcelable {
    @PrimaryKey
    public int id;
    public String link;
    public String title;
    public String summ;
    public String content;
    public String time;
    public String post_time;
    public String source;
    public String view;
    public String thumb_img;
    public boolean isHot;
    public boolean isRead;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.link);
        dest.writeString(this.title);
        dest.writeString(this.summ);
        dest.writeString(this.content);
        dest.writeString(this.time);
        dest.writeString(this.post_time);
        dest.writeString(this.source);
        dest.writeString(this.view);
        dest.writeString(this.thumb_img);
        dest.writeByte(this.isHot ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isRead ? (byte) 1 : (byte) 0);
    }

    public Article() {
    }

    protected Article(Parcel in) {
        this.id = in.readInt();
        this.link = in.readString();
        this.title = in.readString();
        this.summ = in.readString();
        this.content = in.readString();
        this.time = in.readString();
        this.post_time = in.readString();
        this.source = in.readString();
        this.view = in.readString();
        this.thumb_img = in.readString();
        this.isHot = in.readByte() != 0;
        this.isRead = in.readByte() != 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
