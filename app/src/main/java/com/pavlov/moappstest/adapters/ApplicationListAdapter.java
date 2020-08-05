package com.pavlov.moappstest.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pavlov.moappstest.R;
import com.pavlov.moappstest.pojo.Application;
import com.pavlov.moappstest.screen.applicationwebview.ApplicationActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ApplicationListAdapter extends  RecyclerView.Adapter<ApplicationListAdapter.ApplicationViewHolder> {
    private List<Application> applications;

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_item, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ApplicationViewHolder holder, int position) {
        final Application application = applications.get(position);

        holder.appName.setText(application.getApplicationName());
        if(application.isPayment())
            holder.isPayTV.setText("Сплачене");
        else
            holder.isPayTV.setText("Не сплачене");
        if(application.isApplicationStatus()) {
            holder.isEndTV.setText("Завершене");
            holder.isEndImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.icon_zakoncheno));
        }else {
            holder.isEndTV.setText("Незавершене");
            holder.isEndImage.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.icon_nezakoncheno));
        }
        Picasso.get()
                .load(application.getApplicationIcoUrl())
                .placeholder(holder.itemView.getResources().getDrawable(R.drawable.img))
                .into(holder.appImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ApplicationActivity.class);
                intent.putExtra("appUrl", application.getApplicationUrl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    class ApplicationViewHolder extends RecyclerView.ViewHolder{
        private TextView appName;
        private TextView isPayTV;
        private TextView isEndTV;
        private ImageView isEndImage;
        private ImageView appImage;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.appName);
            appImage = itemView.findViewById(R.id.appImage);
            isPayTV = itemView.findViewById(R.id.isPayText);
            isEndTV = itemView.findViewById(R.id.isEndText);
            isEndImage = itemView.findViewById(R.id.isEndImage);
        }
    }
}
