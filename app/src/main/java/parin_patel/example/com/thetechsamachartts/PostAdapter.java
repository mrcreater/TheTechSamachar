package parin_patel.example.com.thetechsamachartts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by parin on 17/9/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostItems> postItems;
    private Context context;
  //  private String title;

    public PostAdapter(List<PostItems> postItems, Context context) {
        this.postItems = postItems;
        this.context = context;
    //    this.title = title;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customlistview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PostItems postItem = postItems.get(position);

        holder.txtTitle.setText(postItem.getTitle());

        Picasso.with(context)
                .load(postItem.getImage())
                .into(holder.imgView);

        holder.txtauthor.setText(postItem.getAuthor());
//        holder.txtdate.setText(postItem.getDate());
     //   holder.conten2nd.setText(postItem.getContent());




    }


    @Override
    public int getItemCount() {
        return postItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TextView txtauthor;
        public ImageView imgView;
        public CardView cardlayout;
     //   public TextView conten2nd;
        public RelativeLayout rellayout;
//        public TextView txtdate;


    public ViewHolder(final View itemView) {
        super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
//      txtdate = (TextView) itemView.findViewById(R.id.date);
             txtauthor = (TextView) itemView.findViewById(R.id.author);
//      I = (ImageView) itemView.findViewById(R.id.imgView);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
      rellayout = (RelativeLayout) itemView.findViewById(R.id.rellayout);
           cardlayout =  (CardView) itemView.findViewById(R.id.cardlayout);
    //    conten2nd = (TextView) itemView.findViewById(R.id.txtcontent);

        cardlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent demo = new Intent(context, MainActivity2.class);
                demo.putExtra("title",txtTitle.getText().toString());
                demo.putExtra("image",imgView.getImageAlpha());
                demo.putExtra("author",txtauthor.getText().toString());
                v.getContext().startActivity(demo);

            }
        });

    }

}
}
