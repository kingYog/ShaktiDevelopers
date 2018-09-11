package com.yogeshborhade.shaktidevelopers.HomePageFolder.CustomerDetailsPdf;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseCustomerClass;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

public class AdapterGetAllCustomer extends RecyclerView.Adapter<AdapterGetAllCustomer.MyViewHolder> {

    private List<DataBaseCustomerClass> dataBaseCustomerClassList;
    Context context;
    View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textCustomerID, textCustomerName, textCustomerMobileNumber, textCustomerAddress, textCustomerAge;

        public MyViewHolder(View view) {
            super(view);
            textCustomerID = (TextView) view.findViewById(R.id.mNumber);
            textCustomerName = (TextView) view.findViewById(R.id.mName);
            textCustomerMobileNumber = (TextView) view.findViewById(R.id.mMobileNumber);
            textCustomerAddress = (TextView) view.findViewById(R.id.mAddress);
            textCustomerAge = (TextView) view.findViewById(R.id.mAge);

        }
    }


    public AdapterGetAllCustomer(List<DataBaseCustomerClass> dataBaseCustomerClassList) {
        this.dataBaseCustomerClassList = dataBaseCustomerClassList;
    }

    @Override
    public AdapterGetAllCustomer.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_get_all_customer, parent, false);

        return new AdapterGetAllCustomer.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterGetAllCustomer.MyViewHolder holder, int position) {
        final DataBaseCustomerClass customerPojo = dataBaseCustomerClassList.get(position);

//        holder.textCustomerID.setText(String.valueOf(customerPojo.getCustomerID()));
        holder.textCustomerID.setText(String.valueOf(position + 1));
        holder.textCustomerName.setText(customerPojo.getCustomerName());
        holder.textCustomerMobileNumber.setText(customerPojo.getCustomerMobileNumber());
        holder.textCustomerAddress.setText(customerPojo.getCustomerAddress());
        holder.textCustomerAge.setText(customerPojo.getCustomerAge());


    }


    @Override
    public int getItemCount() {
        return dataBaseCustomerClassList.size();
    }


}





