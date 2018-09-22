package daluobo.cnbetamobile.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by daluobo on 2016/10/24.
 */
@Entity(tableName = "comment")
public class Comment implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int commentId;
    public String link;
    public String content;
    public String conBox;
    public String location;
    public String article;
    public Integer articleId;
    public String author;
    public String userName;
    public String time;
    public String up;
    public String down;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.commentId);
        dest.writeString(this.link);
        dest.writeString(this.content);
        dest.writeString(this.conBox);
        dest.writeString(this.location);
        dest.writeString(this.article);
        dest.writeValue(this.articleId);
        dest.writeString(this.author);
        dest.writeString(this.userName);
        dest.writeString(this.time);
        dest.writeString(this.up);
        dest.writeString(this.down);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.id = in.readInt();
        this.commentId = in.readInt();
        this.link = in.readString();
        this.content = in.readString();
        this.conBox = in.readString();
        this.location = in.readString();
        this.article = in.readString();
        this.articleId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.author = in.readString();
        this.userName = in.readString();
        this.time = in.readString();
        this.up = in.readString();
        this.down = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
