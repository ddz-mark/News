package com.dudaizhong.news.modules.zhihu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.BannerViewHolder;
import com.dudaizhong.news.modules.zhihu.adapter.viewholder.DailyViewHolder;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;
import java.util.List;

/**
 * Created by Dudaizhong on 2016/9/21.
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int BANNER = 0;
    private final int CONTENT = 1;

    private Context context;
    private BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener;

    private List<ZhihuList.StoriesBean> datas;
    private List<ZhihuList.TopStoriesBean> topdatas;
    private BannerAdapter bannerAdapter;

    public DailyAdapter(Context context, List<ZhihuList.StoriesBean> datas, List<ZhihuList.TopStoriesBean> topdatas) {
        this.context = context;
        this.datas = datas;
        this.topdatas = topdatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            bannerAdapter = new BannerAdapter(context, topdatas);
            return new BannerViewHolder(context, parent, bannerAdapter, onRecyclerViewListener);
        } else {
            return new DailyViewHolder(context, parent, onRecyclerViewListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case BANNER:
                ((BaseViewHolder) holder).bindData(topdatas);
                break;
            case CONTENT:
                ((BaseViewHolder) holder).bindData(datas.get(position - 1));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else {
            return CONTENT;
        }
    }

    public void addDatas(ZhihuList zhihuList) {
        datas = zhihuList.getStories();
        topdatas = zhihuList.getTop_stories();
        bannerAdapter.notifyDataSetChanged();
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewListener(BaseViewHolder.OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
