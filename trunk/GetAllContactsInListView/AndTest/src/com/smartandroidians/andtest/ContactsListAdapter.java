package com.smartandroidians.andtest;

import java.util.ArrayList;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContactsListAdapter extends BaseAdapter {
	
	private Context mContext;
	private LayoutInflater inflater;
	private ArrayList<AppContacts> contactsList;
	
	public ContactsListAdapter(Context context, ArrayList<AppContacts> contactsList) {
		mContext = context;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.contactsList = contactsList;
	}
	
	static class ViewHolder {
		ImageView ivContactsIcon;
		TextView tvContactsName;
		TextView tvPhoneNumber;
		TextView tvEmail;
	}

	@Override
	public int getCount() {
		return contactsList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = (RelativeLayout) inflater.inflate(R.layout.layout_contact_list_item, null);
			holder.ivContactsIcon = (ImageView) convertView.findViewById(R.id.iv_profile_img);
			holder.tvContactsName = (TextView) convertView.findViewById(R.id.tv_contact_name);
			holder.tvPhoneNumber = (TextView) convertView.findViewById(R.id.tv_phone_number);
			holder.tvEmail = (TextView) convertView.findViewById(R.id.tv_email);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (contactsList.get(position).getPhotoUri() != null && !contactsList.get(position).getPhotoUri().isEmpty())
			holder.ivContactsIcon.setImageURI(Uri.parse(contactsList.get(position).getPhotoUri()));
		else 
			holder.ivContactsIcon.setImageURI(Uri.parse("android.resource://"+mContext.getPackageName()+"/"+R.drawable.profile_img));
		holder.tvContactsName.setText(contactsList.get(position).getName());
		holder.tvPhoneNumber.setText(contactsList.get(position).getPhoneNumber());
		holder.tvEmail.setText(contactsList.get(position).getEmail());
		return convertView;
	}

}
