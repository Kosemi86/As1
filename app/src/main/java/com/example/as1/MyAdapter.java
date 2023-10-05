package com.example.as1;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<String> arrayList;
    Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        listener = clickListener;
    }

    public MyAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.sample, parent, false);


        return new MyViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView deleteImageView;
        private ImageView editImageView;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.Text_view);
            deleteImageView = itemView.findViewById(R.id.delete_id);
            editImageView = itemView.findViewById(R.id.edit_id);

            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create an intent to start the ActivityDetails activity
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("item", arrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

            editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Show a dialog to update the file name and description
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    View dialogView = inflater.inflate(R.layout.dialog_update, null);
                    EditText fileNameEditText = dialogView.findViewById(R.id.file_name_edit_text);
                    EditText descriptionEditText = dialogView.findViewById(R.id.description_edit_text);
                    fileNameEditText.setText(arrayList.get(getAdapterPosition()));
                    String[] parts = arrayList.get(getAdapterPosition()).split(" - ");
                    if (parts.length > 1) {
                        descriptionEditText.setText(parts[1]);
                    }
                    builder.setView(dialogView)
                            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String fileName = fileNameEditText.getText().toString().trim();
                                    String description = descriptionEditText.getText().toString().trim();
                                    if (!fileName.isEmpty()) {
                                        String newItem = fileName;
                                        if (!description.isEmpty()) {
                                            newItem += " - " + description;
                                        }
                                        arrayList.set(getAdapterPosition(), newItem);
                                        notifyDataSetChanged();
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }
}
