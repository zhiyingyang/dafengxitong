package com.yikaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikaobao.R;
import com.yikaobao.activity.CandidatesListActivity;
import com.yikaobao.base.BaseApplication;
import com.yikaobao.data.DataCaseList;
import com.yikaobao.view.percentsuppor.PercentLinearLayout;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lx on 2017/5/18.
 */
//考站列表
public class CaseListAdapter extends RecyclerView.Adapter<CaseListAdapter.MyViewHolder> implements View.OnClickListener {


    /**
     * Created by Administrator on 2016/8/3.
     */
    private LinkedList<DataCaseList.DataBean> mDatas = new LinkedList<DataCaseList.DataBean>();
    private Context mContext;
    private LayoutInflater inflater;

    public CaseListAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * 添加数据
     */
    public void addData(List<DataCaseList.DataBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addDataOne(DataCaseList.DataBean datas) {
        mDatas.addLast(datas);
        notifyDataSetChanged();
    }


    public void changeData(DataCaseList.DataBean data, int position) {
        mDatas.set(position, data);
        this.notifyItemChanged(position);
    }

    /**
     * 刷新数据
     */
    public void upData(List<DataCaseList.DataBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public LinkedList<DataCaseList.DataBean> getDatas() {
        return mDatas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item_score_item, null, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemScoreItem.setText(mDatas.get(position).getName());
//        if (mDatas.get(position).getExamier()== BaseApplication.user.getData().getId()){
//            holder.itemScoreEv.setText("考官");
//        }else{
            holder.itemScoreEv.setText("");
 //       }

        holder.itemSelectRoomParent.setOnClickListener(this);
        holder.itemSelectRoomParent.setTag(position);

    }

    @Override
    public void onClick(final View v) {
        final int position = (int) v.getTag();
        switch (v.getId()) {
            case R.id.item_select_room_parent:
                Intent intent = new Intent(mContext, CandidatesListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("stationId", mDatas.get(position).getId());
                intent.putExtra("state", mDatas.get(position).getState());
                intent.putExtra("title", mDatas.get(position).getName());
                mContext.startActivity(intent);

                break;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_score_item)
        TextView itemScoreItem;
        @Bind(R.id.item_score_ev)
        TextView itemScoreEv;
        @Bind(R.id.item_select_room_parent)
        PercentLinearLayout itemSelectRoomParent;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    /**
     * Created by xiehui on 2017/2/23.
     */
    public static class RecycleItemTouchHelper extends ItemTouchHelper.Callback{
        private static final String TAG ="RecycleItemTouchHelper" ;
        private final ItemTouchHelperCallback helperCallback;

        public RecycleItemTouchHelper(ItemTouchHelperCallback helperCallback) {
            this.helperCallback = helperCallback;
        }

        /**
         * 设置滑动类型标记
         *
         * @param recyclerView
         * @param viewHolder
         * @return
         *          返回一个整数类型的标识，用于判断Item那种移动行为是允许的
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            Log.e(TAG, "getMovementFlags: " );
            //START  右向左 END左向右 LEFT  向左 RIGHT向右  UP向上
            //如果某个值传0，表示不触发该操作
            return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.END );
        }
        /**
         * Item是否支持长按拖动
         *
         * @return
         *          true  支持长按操作
         *          false 不支持长按操作
         */
        @Override
        public boolean isLongPressDragEnabled() {
            return super.isLongPressDragEnabled();
        }
        /**
         * Item是否支持滑动
         *
         * @return
         *          true  支持滑动操作
         *          false 不支持滑动操作
         */
        @Override
        public boolean isItemViewSwipeEnabled() {
            return super.isItemViewSwipeEnabled();
        }
        /**
         * 拖拽切换Item的回调
         *
         * @param recyclerView
         * @param viewHolder
         * @param target
         * @return
         *          如果Item切换了位置，返回true；反之，返回false
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Log.e(TAG, "onMove: " );
            helperCallback.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return true;
        }
        /**
         * 滑动Item
         *
         * @param viewHolder
         * @param direction
         *           Item滑动的方向
         */
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.e(TAG, "onSwiped: ");
            helperCallback.onItemDelete(viewHolder.getAdapterPosition());
        }
        /**
         * Item被选中时候回调
         *
         * @param viewHolder
         * @param actionState
         *          当前Item的状态
         *          ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
         *          ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
         *          ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
        }
        /**
         * 移动过程中绘制Item
         *
         * @param c
         * @param recyclerView
         * @param viewHolder
         * @param dX
         *          X轴移动的距离
         * @param dY
         *          Y轴移动的距离
         * @param actionState
         *          当前Item的状态
         * @param isCurrentlyActive
         *          如果当前被用户操作为true，反之为false
         */
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            //滑动时自己实现背景及图片
            if (actionState==ItemTouchHelper.ACTION_STATE_SWIPE){

                //dX大于0时向右滑动，小于0向左滑动
                View itemView=viewHolder.itemView;//获取滑动的view
                Resources resources= BaseApplication.myApplication.getResources();
                Bitmap bitmap= BitmapFactory.decodeResource(resources, R.drawable.icon_back);//获取删除指示的背景图片
                int padding =10;//图片绘制的padding
                int maxDrawWidth=2*padding+bitmap.getWidth();//最大的绘制宽度
                Paint paint=new Paint();
                paint.setColor(resources.getColor(R.color.btninvalid));
                int x=Math.round(Math.abs(dX));
                int drawWidth=Math.min(x,maxDrawWidth);//实际的绘制宽度，取实时滑动距离x和最大绘制距离maxDrawWidth最小值
                int itemTop=itemView.getBottom()-itemView.getHeight();//绘制的top位置
                //向右滑动
                if(dX>0){
                    //根据滑动实时绘制一个背景
                    c.drawRect(itemView.getLeft(),itemTop,drawWidth,itemView.getBottom(),paint);
                    //在背景上面绘制图片
                    if (x>padding){//滑动距离大于padding时开始绘制图片
                        //指定图片绘制的位置
                        Rect rect=new Rect();//画图的位置
                        rect.left=itemView.getLeft()+padding;
                        rect.top=itemTop+(itemView.getBottom()-itemTop-bitmap.getHeight())/2;//图片居中
                        int maxRight=rect.left+bitmap.getWidth();
                        rect.right=Math.min(x,maxRight);
                        rect.bottom=rect.top+bitmap.getHeight();
                        //指定图片的绘制区域
                        Rect rect1=null;
                        if (x<maxRight){
                            rect1=new Rect();//不能再外面初始化，否则dx大于画图区域时，删除图片不显示
                            rect1.left=0;
                            rect1.top = 0;
                            rect1.bottom=bitmap.getHeight();
                            rect1.right=x-padding;
                        }
                        c.drawBitmap(bitmap,rect1,rect,paint);
                    }
                    float alpha = 1.0f - Math.abs(dX) / (float) itemView.getWidth();
                    itemView.setAlpha(alpha);
                    //绘制时需调用平移动画，否则滑动看不到反馈
                    itemView.setTranslationX(dX);
                }else {
                    //如果在getMovementFlags指定了向左滑动（ItemTouchHelper。START）时则绘制工作可参考向右的滑动绘制，也可直接使用下面语句交友系统自己处理
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }else {
                //拖动时有系统自己完成
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }


        }


        public interface ItemTouchHelperCallback{
            void onItemDelete(int positon);
            void onMove(int fromPosition, int toPosition);
        }
    }
}
