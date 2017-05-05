package lets.android.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lombok.Getter;

public abstract class BaseBindingViewHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    @Getter
    private VDB viewDataBinding;

    public BaseBindingViewHolder(View itemView) {

        super(itemView);
        this.viewDataBinding = DataBindingUtil.bind(itemView);
    }
}
