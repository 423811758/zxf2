package com.wzd.zxf;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzd.wolf_open_resource.app.CustomAdapter;
import com.wzd.wolf_open_resource.util.Log4JUtil;
import com.wzd.zxf.model.Fund;

/**
 * Created by wzd on 2017/11/19.
 */

public class FundAdapter extends CustomAdapter<Fund> {

    public FundAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null) {
            view = inflater(R.layout.item_fund, null);
            holder = new ViewHolder();
            holder.codeTv = (TextView) view.findViewById(R.id.fund_code_tv);
            holder.nameTv = (TextView) view.findViewById(R.id.fund_name_tv);
            holder.increaseTv = (TextView) view.findViewById(R.id.fund_increase_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Fund fund = getItem(position);
        holder.codeTv.setText(fund.getFundcode());
        holder.nameTv.setText(fund.getName());
        holder.increaseTv.setText(fund.getGszzl()+"%");
        Log4JUtil.debugInfo(fund.getName());
        return view;
    }

    class ViewHolder{
        TextView codeTv;
        TextView nameTv;
        TextView increaseTv;
    }
}
