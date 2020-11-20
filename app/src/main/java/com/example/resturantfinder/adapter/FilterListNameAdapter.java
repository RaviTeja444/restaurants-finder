package com.example.resturantfinder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.resturantfinder.R;
import com.example.resturantfinder.utils.Filtermethods;
import java.util.List;


public class FilterListNameAdapter extends RecyclerView.Adapter<FilterListNameAdapter.RecentsViewHolder> {
    private List<String> filterList ;
    private Context context;
    private Filtermethods filtermethods;

    public FilterListNameAdapter(List<String> filterList, Context context, Filtermethods filtermethods) {
        this.filterList = filterList;
        this.context = context;
        this.filtermethods = filtermethods;
    }



    @NonNull
    @Override
    public FilterListNameAdapter.RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.slecct_filter_list, viewGroup, false);
        return new FilterListNameAdapter.RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder recentsViewHolder, final int i) {
        final String filterName =filterList.get(i);
        recentsViewHolder.item.setText( filterName);
        recentsViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtermethods.setFilter(filterName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        LinearLayout layout;
        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.filterListss);
            layout=itemView.findViewById(R.id.filterListssLayout);

        }
    }

}
