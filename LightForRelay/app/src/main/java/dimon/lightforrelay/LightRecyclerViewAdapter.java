package dimon.lightforrelay;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class LightRecyclerViewAdapter extends RecyclerView.Adapter<LightRecyclerViewAdapter.LightViewHodler> {

    private List<Light> data;
    private OnItemClickLister onItemClickListener;
    private onRecyclerItemLongClickLister onRecyclerItemLongClickLister;
    private Context context;

    public LightRecyclerViewAdapter(Context context, List<Light> data) {
        this.context = context;
        this.data = data;
    }

    public void setReOnItemClickListener(OnItemClickLister onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setReOnItemLongClickListener(onRecyclerItemLongClickLister onRecyclerItemLongClickLister) {
        this.onRecyclerItemLongClickLister = onRecyclerItemLongClickLister;
    }

    @Override
    public LightViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, null);
        LightViewHodler viewHodler = new LightViewHodler(view);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(LightViewHodler holder, int position) {
        holder.txt.setText(data.get(position).getName());
        holder.itemView.setTag(position);
        holder.bottom_text.setText(data.get(position).getBottom_txt());
        if (data.get(position).getOpen()) {
            holder.card.setCardBackgroundColor(this.context.getResources().getColor(R.color.colorPrimary));
            holder.txt.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.card.setCardBackgroundColor(this.context.getResources().getColor(R.color.white));
            holder.txt.setTextColor(context.getResources().getColor(R.color.black));
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class LightViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txt, bottom_text;
        public ImageView img;
        public CardView card;

        public LightViewHodler(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
            img = (ImageView) itemView.findViewById(R.id.img);
            card = (CardView) itemView.findViewById(R.id.card);
            bottom_text = (TextView) itemView.findViewById(R.id.bottom_text);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.setRecyclerItemClickLister(v, (Integer) itemView.getTag());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return (onRecyclerItemLongClickLister != null)
                    && onRecyclerItemLongClickLister.setRecyclerItemLongClickListener(v, (Integer) itemView.getTag());
        }
    }

    public interface OnItemClickLister {
        void setRecyclerItemClickLister(View v, int position);
    }

    public interface onRecyclerItemLongClickLister {
        boolean setRecyclerItemLongClickListener(View v, int position);
    }

}
