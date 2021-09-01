package com.example.abishek.single_user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Library_Adapter extends RecyclerView.Adapter<Library_Adapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<Library_Item> library_list;

    public Library_Adapter(Context context, ArrayList<Library_Item> exampleList) {
        mContext = context;
        library_list = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.library_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Library_Item items = library_list.get(position);

        String book_title = items.getBooktitle();
        int book_account = items.getBookAccount();
        String book_Resource = items.getBookResource();
        String book_IssueDate = items.getBookIssueDate();
        String book_DueDate = items.getBookDueDate();
        String book_ReturnDate = items.getBookReturnDate();
        String book_Status = items.getBookStatus();

        holder.Htv_bookTitle.setText(book_title);
        holder.Htv_accountNo.setText("" + book_account);
        holder.Htv_bookResource.setText(book_Resource);
        holder.Htv_bookIssueDate.setText(book_IssueDate);
        holder.Htv_bookDueDate.setText(book_DueDate);
        holder.Htv_bookReturnDate.setText(book_ReturnDate);
        holder.Htv_bookStatus.setText(book_Status);
    }

    @Override
    public int getItemCount() {
        return library_list.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView Htv_bookTitle;
        public TextView Htv_accountNo;
        public TextView Htv_bookResource;
        public TextView Htv_bookIssueDate;
        public TextView Htv_bookDueDate;
        public TextView Htv_bookReturnDate;
        public TextView Htv_bookStatus;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            Htv_bookTitle = itemView.findViewById(R.id.tv_bookTitle);
            Htv_accountNo = itemView.findViewById(R.id.tv_accountNo);
            Htv_bookResource = itemView.findViewById(R.id.tv_bookResource);
            Htv_bookIssueDate = itemView.findViewById(R.id.tv_bookIssueDate);
            Htv_bookDueDate = itemView.findViewById(R.id.tv_bookDueDate);
            Htv_bookReturnDate = itemView.findViewById(R.id.tv_bookReturnDate);
            Htv_bookStatus = itemView.findViewById(R.id.tv_bookStatus);
        }
    }
}

