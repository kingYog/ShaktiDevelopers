package com.yogeshborhade.shaktidevelopers.HomePageFolder.ProjectExpence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.DatabaseClas.DataBaseExpenceClas;
import com.yogeshborhade.shaktidevelopers.R;

import java.util.List;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.numberWithComma;

/**
 * Created by admin on 30-03-2018.
 */

public class AdapterExpence extends RecyclerView.Adapter<AdapterExpence.MyViewHolder> {

    private List<DataBaseExpenceClas> dataBaseExpenceClasList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textExpenceId, textExpenceName, textEcpenceAmount, textExpenceDate,textExpenceMode,textExpenceModeNumber;
        public Button btnEditExpence;
        public LinearLayout mainLayout,expenceNumber;

        public MyViewHolder(View view) {
            super(view);
            textExpenceId = (TextView) view.findViewById(R.id.mtextExpenceID);
            textExpenceName = (TextView) view.findViewById(R.id.mtextExpanceName);
            textEcpenceAmount = (TextView) view.findViewById(R.id.mtextExpenceAmount);
            textExpenceDate = (TextView) view.findViewById(R.id.mtextExpenceDate);

            textExpenceMode = (TextView) view.findViewById(R.id.mtextExpenceType);
            textExpenceModeNumber = (TextView) view.findViewById(R.id.mtextExpenceTypeNumber);

            btnEditExpence = (Button) view.findViewById(R.id.mbuttonEditExpence);
            mainLayout = (LinearLayout) view.findViewById(R.id.rowmainLayour);
            expenceNumber = (LinearLayout) view.findViewById(R.id.mllExpenceNumber);

        }
    }


    public AdapterExpence(List<DataBaseExpenceClas> dataBaseExpenceClasList) {
        this.dataBaseExpenceClasList = dataBaseExpenceClasList;
    }

    @Override

    public AdapterExpence.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_expence, parent, false);

        return new AdapterExpence.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AdapterExpence.MyViewHolder holder, int position) {
        final DataBaseExpenceClas dataBaseExpenceClas = dataBaseExpenceClasList.get(position);


        //holder.textExpenceId.setText(String.valueOf(dataBaseExpenceClas.getExpenceID()));

        holder.textExpenceId.setText(String.valueOf(position+1));
        holder.textExpenceName.setText(dataBaseExpenceClas.getExpenceName());
        holder.textEcpenceAmount.setText(numberWithComma.format(Double.valueOf(dataBaseExpenceClas.getExpencePrice())));
        holder.textExpenceDate.setText(dataBaseExpenceClas.getExpenceDate());

        holder.textExpenceMode.setText(dataBaseExpenceClas.getExpencePaymentMode());
        holder.textExpenceModeNumber.setText(dataBaseExpenceClas.getExpencePaymentNumber());



        if(dataBaseExpenceClas.getExpencePaymentMode().equals(Constants.TRANSACTION_TYPE_CASH)){
            holder.expenceNumber.setVisibility(View.GONE);
        }else {
            holder.expenceNumber.setVisibility(View.VISIBLE);
        }

        holder.btnEditExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Edit Expence", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnEditExpence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ExpenceDetail.class);
                i.putExtra("projectID", String.valueOf(dataBaseExpenceClas.getProject_Id()));
                i.putExtra("expenceID", String.valueOf(dataBaseExpenceClas.getExpenceID()));

                ((Activity) v.getContext()).startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataBaseExpenceClasList.size();
    }
}




