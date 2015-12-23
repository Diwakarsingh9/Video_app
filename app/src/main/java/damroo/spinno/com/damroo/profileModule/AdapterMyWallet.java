package damroo.spinno.com.damroo.profileModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import damroo.spinno.com.damroo.R;

/**
 * Created by spinnosolutions on 9/15/15.
 */
public class AdapterMyWallet extends BaseAdapter {

    Context con ;
    String [] wallets ;
    String [] walletmoney ;
    LayoutInflater inflater ;


    public AdapterMyWallet(Context con , String [] wallets  ,String [] walletmoney){
        this.con = con ;
        this.wallets = wallets ;
        this.walletmoney = walletmoney ;
        inflater = (LayoutInflater) con.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return wallets.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.item_mywalletss, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.wallet_name.setText(wallets[position]);
        holder.wallet_money.setText(walletmoney[position]);


        return view;
    }

    static class ViewHolder {

        @Bind(R.id.wallet_name) TextView wallet_name;
        @Bind(R.id.wallet_money) TextView wallet_money;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
