package com.example.dev.logobin.home;


import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dev.logobin.Network.GetDastebandi;
import com.example.dev.logobin.R;
import com.example.dev.logobin.fragment.FragmentActivity;

import com.example.dev.logobin.fragment.FragmentView;
import com.example.dev.logobin.handel.Adapter_Daste_Brand;
import com.example.dev.logobin.model.Dastebandi;
import com.example.dev.logobin.model.Dastebandi_zirdaste;

import com.example.dev.logobin.model.M_ZirDaste;
import com.example.dev.logobin.ui.Show_All_Item;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DasteUI extends FragmentView {

    private Adapter_categury adapter_dastebandi;
    private ArrayList<Dastebandi_zirdaste> zirmenu;
    private RecyclerView recyclerViewDastebandi ,recycler_filter;
    public static ArrayList<M_ZirDaste> Full_List;



    @Override
    public void OnCreate() {
        View view = LayoutInflater.from(Activity).inflate( R.layout.home_daste_ui,null);

         recyclerViewDastebandi= (RecyclerView) view.findViewById(R.id.Dastebandi_Recyclerview);
         recycler_filter=(RecyclerView) view.findViewById(R.id.daste_recycler_daste);
        recycler_filter.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.HORIZONTAL,true));
        recycler_filter.setHasFixedSize(true);

         list_dastebandi();


        recyclerViewDastebandi.setLayoutManager(new LinearLayoutManager(Activity,LinearLayoutManager.VERTICAL,false));
        recyclerViewDastebandi.setHasFixedSize(true);

        ViewMain=view;


    }


    public void getId(String title){
        for (M_ZirDaste getid : Full_List){
            if (getid.getTitle().equals(title)){
                Toast.makeText(Activity, ""+getid.getId(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sublist_dastebandi(String id){
        GetDastebandi getDastebandi=new GetDastebandi(Activity);
        getDastebandi.DasteBandiFull(id, new GetDastebandi.back_daste_full() {
            @Override
            public void onsuccess(ArrayList<M_ZirDaste> ListAdapter, ArrayList<M_ZirDaste> full_list) {
                adapter_dastebandi=new Adapter_categury(ListAdapter,Activity,Activity);
                recyclerViewDastebandi.setAdapter(adapter_dastebandi);
                Full_List=new ArrayList<>(full_list);
            }

            @Override
            public void faild(String eror) {

            }
        });
    }


    private void list_dastebandi(){
        GetDastebandi dastebandi=new GetDastebandi(Activity);
        dastebandi.Daste_part(new GetDastebandi.callback() {
            @Override
            public void onsuccess(ArrayList<M_ZirDaste> listss) {
                sublist_dastebandi(listss.get(0).getId());
                Adapter_Daste_Brand adapter_daste=new Adapter_Daste_Brand(listss, new Adapter_Daste_Brand.OnItemClickListener() {
                    @Override
                    public void onItemClick(M_ZirDaste m_zirDaste) {
                        Toast.makeText(Activity, ""+m_zirDaste.getId(), Toast.LENGTH_SHORT).show();
                        sublist_dastebandi(m_zirDaste.getId());
                    }
                });
                recycler_filter.setAdapter(adapter_daste);
            }

            @Override
            public void faild(String eror) {

            }
        });
    }




    public void setZirmenu() {

        zirmenu=new ArrayList<>();


        ArrayList<Dastebandi> Nan_ghalat= new ArrayList<>();
        Nan_ghalat.add(new Dastebandi("برنج"));
        Nan_ghalat.add(new Dastebandi("نان"));
        Nan_ghalat.add(new Dastebandi("گندم"));
        Nan_ghalat.add(new Dastebandi("جو"));
        Nan_ghalat.add(new Dastebandi("ذرت"));
        Nan_ghalat.add(new Dastebandi("حبوبات"));
        Nan_ghalat.add(new Dastebandi("سویا"));
        Nan_ghalat.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("نان و غلات حبوبات",Nan_ghalat));


        ArrayList<Dastebandi> Reshteha= new ArrayList<>();
        Reshteha.add(new Dastebandi("ماکارونی"));
        Reshteha.add(new Dastebandi("رشته آش و سوپ"));
        Reshteha.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("رشته ها",Reshteha));

        ArrayList<Dastebandi> Ghand_shekar = new ArrayList<>();
        Ghand_shekar.add(new Dastebandi("قند"));
        Ghand_shekar.add(new Dastebandi("شکر"));
        Ghand_shekar.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("قند و شکر",Ghand_shekar));

        ArrayList<Dastebandi> Roghan = new ArrayList<>();
        Roghan.add(new Dastebandi("روغن مایع"));
        Roghan.add(new Dastebandi("روغن جامد"));
        Roghan.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("روغن",Roghan));

        ArrayList<Dastebandi> Porotoein = new ArrayList<>();
        Porotoein.add(new Dastebandi("اجزای گوسفند"));
        Porotoein.add(new Dastebandi("اجزای گاو و گوساله"));
        Porotoein.add(new Dastebandi("اجزای مرغ و سایر پرندگان"));
        Porotoein.add(new Dastebandi("ماهی ، میگو و خاویار"));
        Porotoein.add(new Dastebandi("سوسیس و کالباس"));
        Porotoein.add(new Dastebandi("تخم مرغ ، بلدرچین و شترمرغ"));
        Porotoein.add(new Dastebandi("قارچ"));
        Porotoein.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("پروتئین",Porotoein));

        ArrayList<Dastebandi> Labaniyat = new ArrayList<>();
        Labaniyat.add(new Dastebandi("شیر"));
        Labaniyat.add(new Dastebandi("ماست"));
        Labaniyat.add(new Dastebandi("کره"));
        Labaniyat.add(new Dastebandi("پنیر"));
        Labaniyat.add(new Dastebandi("خامه"));
        Labaniyat.add(new Dastebandi("کشک"));
        Labaniyat.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("لبنیات",Labaniyat));

        ArrayList<Dastebandi> Miveh_sabzijat = new ArrayList<>();
        Miveh_sabzijat.add(new Dastebandi("میوه ها"));
        Miveh_sabzijat.add(new Dastebandi("صیفی و سبزیجات"));
        Miveh_sabzijat.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("میوه و سبزیجات",Miveh_sabzijat));


        ArrayList<Dastebandi> Nooshidaniha = new ArrayList<>();
        Nooshidaniha.add(new Dastebandi("شربت و آبمیوه"));
        Nooshidaniha.add(new Dastebandi("نوشابه و ماءالشعیر"));
        Nooshidaniha.add(new Dastebandi("دوغ"));
        Nooshidaniha.add(new Dastebandi("آب معدنی"));
        Nooshidaniha.add(new Dastebandi("قهوه"));
        Nooshidaniha.add(new Dastebandi("چای و دمنوش"));
        Nooshidaniha.add(new Dastebandi("عرقیات"));
        Nooshidaniha.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("نوشیدنی ها",Nooshidaniha));

        ArrayList<Dastebandi> Tanagholat = new ArrayList<>();
        Tanagholat.add(new Dastebandi("شکلات و آبنبات"));
        Tanagholat.add(new Dastebandi("بیسکوئیت"));
        Tanagholat.add(new Dastebandi("مغز و تخمه"));
        Tanagholat.add(new Dastebandi("کیک و کلوچه"));
        Tanagholat.add(new Dastebandi("آلوچه و لواشک"));
        Tanagholat.add(new Dastebandi("آدامس"));
        Tanagholat.add(new Dastebandi("پاستیل"));
        Tanagholat.add(new Dastebandi("پودر کیک و ژله"));
        Tanagholat.add(new Dastebandi("پشمک"));
        Tanagholat.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("تنقلات",Tanagholat));

        ArrayList<Dastebandi> Chashni_Afzoodani = new ArrayList<>();
        Chashni_Afzoodani.add(new Dastebandi("سس"));
        Chashni_Afzoodani.add(new Dastebandi("آبلیمو،آبغوره و سرکه"));
        Chashni_Afzoodani.add(new Dastebandi("زعفران،زرشک و لیمو عمانی"));
        Chashni_Afzoodani.add(new Dastebandi("ادویه و نمک"));
        Chashni_Afzoodani.add(new Dastebandi("ارد ، نشاسته"));
        Chashni_Afzoodani.add(new Dastebandi("چاشنی و عصاره"));
        Chashni_Afzoodani.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("چاشنی",Chashni_Afzoodani));

        ArrayList<Dastebandi> Moraba_asal_shireh = new ArrayList<>();
        Moraba_asal_shireh.add(new Dastebandi("مربا"));
        Moraba_asal_shireh.add(new Dastebandi("عسل"));
        Moraba_asal_shireh.add(new Dastebandi("شیره"));
        Moraba_asal_shireh.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("مربا عسل شیره",Moraba_asal_shireh));

        ArrayList<Dastebandi> Ajil_shirini_khoshkbar = new ArrayList<>();
        Ajil_shirini_khoshkbar.add(new Dastebandi("آجیل"));
        Ajil_shirini_khoshkbar.add(new Dastebandi("خشکبار"));
        Ajil_shirini_khoshkbar.add(new Dastebandi("شیرینی"));
        Ajil_shirini_khoshkbar.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("خشکبار",Ajil_shirini_khoshkbar));

        ArrayList<Dastebandi> Shooyandeh_behdashti = new ArrayList<>();
        Shooyandeh_behdashti.add(new Dastebandi("شوینده ظروف"));
        Shooyandeh_behdashti.add(new Dastebandi("شوینده لباس"));
        Shooyandeh_behdashti.add(new Dastebandi("پاک کننده سطوح و لکه بر"));
        Shooyandeh_behdashti.add(new Dastebandi("پنبه، دستمال"));
        Shooyandeh_behdashti.add(new Dastebandi("بهداشت کودک و سالمند"));
        Shooyandeh_behdashti.add(new Dastebandi("بهداشت دهان"));
        Shooyandeh_behdashti.add(new Dastebandi("بهداشت بدن و صورت"));
        Shooyandeh_behdashti.add(new Dastebandi("بهداشت و شست و شوی مو"));
        Shooyandeh_behdashti.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("شوینده بهداشتی",Shooyandeh_behdashti));

        ArrayList<Dastebandi> Arayeshi = new ArrayList<>();
        Arayeshi.add(new Dastebandi("عطر ، ادکلن و اسپری"));
        Arayeshi.add(new Dastebandi("آرایش صورت"));
        Arayeshi.add(new Dastebandi("آرایش چشم"));
        Arayeshi.add(new Dastebandi("آرایش لب"));
        Arayeshi.add(new Dastebandi("آرایش ناخن"));
        Arayeshi.add(new Dastebandi("محصولات پوست"));
        Arayeshi.add(new Dastebandi("آرایش مو"));
        Arayeshi.add(new Dastebandi("لوازم شخصی برقی"));
        Arayeshi.add(new Dastebandi("لوازم شخصی غیر برقی"));
        Arayeshi.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("لوازم آرایشی",Arayeshi));

        ArrayList<Dastebandi> Farhangi_Sargarmi = new ArrayList<>();
        Farhangi_Sargarmi.add(new Dastebandi("فیلم و سریال"));
        Farhangi_Sargarmi.add(new Dastebandi("لوازم التحریر"));
        Farhangi_Sargarmi.add(new Dastebandi("کتاب"));
        Farhangi_Sargarmi.add(new Dastebandi("اسباب بازی"));
        Farhangi_Sargarmi.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("سرگرمی فرهنگی",Farhangi_Sargarmi));

        ArrayList<Dastebandi> Malzoomat_khanedari = new ArrayList<>();
        Malzoomat_khanedari.add(new Dastebandi("واکس ، چسب و کبریت"));
        Malzoomat_khanedari.add(new Dastebandi("ابزار نطافت و نگهداری منزل"));
        Malzoomat_khanedari.add(new Dastebandi("ظروف پلاستیکی و یکبارمصرف"));
        Malzoomat_khanedari.add(new Dastebandi("ابزار برقی و غیر برقی"));
        Malzoomat_khanedari.add(new Dastebandi("سفره و کیسه"));
        Malzoomat_khanedari.add(new Dastebandi("لوازم آشپزخانه"));
        Malzoomat_khanedari.add(new Dastebandi("خشبو کننده"));
        Malzoomat_khanedari.add(new Dastebandi("سایر"));

        zirmenu.add(new Dastebandi_zirdaste("ملزومات خانه داری",Malzoomat_khanedari));




    }
}


class Adapter_categury extends RecyclerView.Adapter<Adapter_categury.Holder>{

    private ArrayList<M_ZirDaste> list;
    private FragmentActivity activity;
    private Context context;

    public Adapter_categury(ArrayList<M_ZirDaste> list, FragmentActivity activity, Context context) {
        this.list = list;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_categury.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.z_m_dastebandi_asli,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_categury.Holder holder, final int position) {
        final M_ZirDaste model=list.get(position);

        holder.daste.setText(model.getSublist());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(new Adapter_child_categury(model.getList(),activity));
        if (model.getClick()){
            holder.recyclerView.setVisibility(View.VISIBLE);
        }else {
            holder.recyclerView.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getClick()){
                    model.setClick(false);
                    notifyItemChanged(position);
                }else {
                    model.setClick(true);
                    notifyItemChanged(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView daste;
        private ExpandableLinearLayout expandable;
        private RecyclerView recyclerView;
        public Holder(View itemView) {
            super(itemView);
            daste=(TextView)itemView.findViewById(R.id.z_model_dastebandi_TextView);
//            expandable=(ExpandableLinearLayout) itemView.findViewById(R.id.Expend_list);
//            expandable.initLayout();
            recyclerView=(RecyclerView)itemView.findViewById(R.id.Expend_Recycler_list);
        }

    }
}

class Adapter_child_categury extends RecyclerView.Adapter<Adapter_child_categury.ViewHolder>{
    private ArrayList<M_ZirDaste> list;
    private FragmentActivity activity;

    public Adapter_child_categury(ArrayList<M_ZirDaste> list, FragmentActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Adapter_child_categury.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_dastebandi_zirdaste,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_child_categury.ViewHolder holder, int position) {
        final M_ZirDaste model=list.get(position);
        holder.zirdaste.setText(model.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(activity, ""+model.getId(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("Daste_Id",model.getId());
                bundle.putString("Daste_Title",model.getTitle());
                Show_All_Item show_all_item=new Show_All_Item();
                show_all_item.setArguments(bundle);

                activity.GetManager().OpenView( show_all_item,"Kala",true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView zirdaste;
        public ViewHolder(View itemView) {
            super(itemView);
            zirdaste=(TextView)itemView.findViewById(R.id.z_model_dastebandi_Textview_child);
        }
    }
}



class DasteViewHolder extends GroupViewHolder{

    private TextView daste;

    public DasteViewHolder(View itemView) {
        super(itemView);
        daste=(TextView)itemView.findViewById(R.id.z_model_dastebandi_TextView);


    }

    public void setDaste_Titel(String name){
        daste.setText(name);


    }

}

class ZirdasteViewHodler extends ChildViewHolder {

    private TextView zirdaste;



    public ZirdasteViewHodler(final View itemView) {
        super(itemView);


        zirdaste=(TextView)itemView.findViewById(R.id.z_model_dastebandi_Textview_child);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (M_ZirDaste getid : DasteUI.Full_List){
                    if (getid.getTitle().equals(zirdaste.getText().toString())){
                        Toast.makeText(itemView.getContext(), ""+getid.getId(), Toast.LENGTH_SHORT).show();

                    }
                }
                Log.i("ZirDaste",zirdaste.getText().toString());


            }
        });


    }

    public  void setZirdaste_Titel(String name){
        zirdaste.setText(name);
    }



}


class Adapter_Dastebandi extends ExpandableRecyclerViewAdapter<DasteViewHolder,ZirdasteViewHodler> {

    public Adapter_Dastebandi(List<? extends ExpandableGroup> groups) {
        super(groups);
    }



    @Override
    public DasteViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_dastebandi_asli,parent,false);


        return new DasteViewHolder(view);
    }

    @Override
    public ZirdasteViewHodler onCreateChildViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_m_dastebandi_zirdaste,parent,false);


        return new ZirdasteViewHodler(view);

    }

    @Override
    public void onBindChildViewHolder(ZirdasteViewHodler holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Dastebandi Listasli = (Dastebandi) group.getItems().get(childIndex);

        holder.setZirdaste_Titel(Listasli.getDaste());




    }

    @Override
    public void onBindGroupViewHolder(DasteViewHolder holder, int flatPosition, ExpandableGroup group) {

        holder.setDaste_Titel(group.getTitle());


    }
}
