# NineGridLayout

## DEMO

![](http://olpu32iyy.bkt.clouddn.com/18-8-17/22405145.jpg)

## 需求

+ 满足0-9个图的适配

## 使用

#### 手动设置

```Java

 <me.sugarkawhi.ninegridlayout.NineGridLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:ngl_gridSpace="10dp"
            app:ngl_oneChildHeight="300dp">
            
             <ImageView
                style="@style/Img"
                android:src="@drawable/ic_fox" />
                
             <ImageView
                style="@style/Img"
                android:src="@drawable/ic_deef" />

</me.sugarkawhi.ninegridlayout.NineGridLayout>

```

#### 在RecyclerView中使用通过设置 NineGridAdapter

NineGridAdapter源码：

```java

public abstract class NineGridAdapter {

    protected abstract View getItemView(ViewGroup parent, int position);

    protected abstract void bindView(View view, int position);

    protected abstract int getItemCount();

    protected void onItemClick(View view, int position) {
    }

}

```

eg:通过继承NineGridAdapter 详见demo

```JAVA
   private static class NineImageAdapter extends NineGridAdapter {

        private List<String> mUrls;

        public NineImageAdapter(List<String> urls) {
            mUrls = urls;
        }

        @Override
        protected View getItemView(ViewGroup parent, int position) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_img, parent, false);
        }

        @Override
        protected void bindView(View view, int position) {
            String url = mUrls.get(position);
            ImageView imageView = view.findViewById(R.id.iv);
            Picasso.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.ic_heart)
                    .into(imageView);
        }

        @Override
        protected int getItemCount() {
            return mUrls == null ? 0 : mUrls.size();
        }

        @Override
        protected void onItemClick(View view, int position) {
            super.onItemClick(view, position);
            Toast.makeText(App.getInstance(), "position " + position, Toast.LENGTH_SHORT).show();
        }
    }

```



## 属性

```java

app:ngl_gridSpace="10dp"
app:ngl_oneChildHeight="200dp"

```

#### 属性说明

|      属性值      |   说明   | 值|
|:---------------:|:-------:|:--:|
|  ngl_gridSpace | 间距 | 默认20px|
|  ngl_oneChildHeight   |   当只有一个图的时候的高度 |默认400px|


