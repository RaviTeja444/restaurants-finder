//package com.example.resturantfinder;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class Restaurant {
//    private static RestaurantAdapter restaurantAdapter;
//
//    public class RestaurantInfo {
//        public String RestaurantEmail;
//        public String RestaurantId;
//        public String RestaurantLocation;
//        public String RestaurantType;
//        public String Website;
//        public String RestaurantName;
//
//        public RestaurantInfo() {
//        }
//
//        public RestaurantInfo(String restaurantEmail, String restaurantId, String restaurantLocation, String restaurantType, String website, String restaurantName) {
//            RestaurantEmail = restaurantEmail;
//            RestaurantId = restaurantId;
//            RestaurantLocation = restaurantLocation;
//            RestaurantType = restaurantType;
//            Website = website;
//            RestaurantName = restaurantName;
//        }
//
//        public String getRestaurantEmail() {
//            return RestaurantEmail;
//        }
//
//        public String getRestaurantId() {
//            return RestaurantId;
//        }
//
//        public String getRestaurantLocation() {
//            return RestaurantLocation;
//        }
//
//        public String getRestaurantType() {
//            return RestaurantType;
//        }
//
//        public String getWebsite() {
//            return Website;
//        }
//
//        public String getRestaurantName() {
//            return RestaurantName;
//        }
//
//    }
//
//
//
//    private static Restaurant singleton = null;
//    private static DatabaseReference resref;
//
//    public static Restaurant getSingleton(ArrayList<RestaurantInfo> resArray) {
////        jobAdapter = new JobAdapter();
//        singleton = new Restaurant(resArray);
//        return singleton;
//    }
//
//
//    public ArrayList<RestaurantInfo> resArray;
//
//    private Restaurant(ArrayList<RestaurantInfo> resArray) {
//        this.resArray = resArray;
//        resref = JobsActivity.jobref;
////        loadModel();
//    }
//
//    private void loadModel() {
//        resref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    postViewModel.jobs joblist = data.getValue(postViewModel.jobs.class);
//                    if (joblist.getMajor().equalsIgnoreCase(JobsActivity.courseMajor)) {
//                        String = joblist.getJobtitle();
//                        String  = joblist.getJobtitle();
//                        String  = joblist.getJobdescription();
//                        String  = joblist.getMajor();
//                        String  = joblist.getQualifications();
//                        String  = joblist.getDeadline();
//
//                        resArray.add(new RestaurantInfo(name, jobtitle, jobdescription, major, qualifications, deadline));
//                    }
//                }
//                jobAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println("database error");
//            }
//        });
//    }
//}
//
//
//class ResAdapter extends RecyclerView.Adapter<ResAdapter.ResViewHolder> {
//
//    public static String jobName, jobTitle, jobDesc, studentMajor, qualification, deadline;
//
//    public static class ResViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private LinearLayout reference;
//        MyOnClick myOnClick;
//        private Button applyBTN;
//
//        public ResViewHolder(@NonNull LinearLayout linearLayout, MyOnClick myOnClick) {
//            super(linearLayout);
//            this.reference = linearLayout;
//            this.myOnClick = myOnClick;
//        }
//
//        @Override
//        public void onClick(View v) {
//        }
//    }
//
//    private MyOnClick myOnClick;
//
//
//    Restaurant jobsModel;
//
//    public ResAdapter(MyOnClick myOnClick, ArrayList<Restaurant.JobsModelInfo> jobsArray) {
//        super();
//        jobsModel = JobsModel.getSingleton(jobsArray);
//        this.myOnClick = myOnClick;
//    }
//
//
//    @Override
//    public JobViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_rest_list, parent, false);
//        JobViewHolder jobViewHolder = new JobViewHolder(v, myOnClick);
//        return jobViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
//        final TextView jobNameTV = holder.reference.findViewById(R.id.jobnameTV);
//        jobName = jobsModel.jobsArray.get(position).name;
//        jobNameTV.setText(jobsModel.jobsArray.get(position).name);
//
//        final TextView jobTitleTV = holder.reference.findViewById(R.id.jobtitleTV);
//        jobTitle = jobsModel.jobsArray.get(position).jobtitle;
//        jobTitleTV.setText(jobsModel.jobsArray.get(position).jobtitle);
//
//        final TextView jobDescTV = holder.reference.findViewById(R.id.jobdescTV);
//        jobDesc = jobsModel.jobsArray.get(position).jobdescription;
//        jobDescTV.setText(jobsModel.jobsArray.get(position).jobdescription);
//
//        final TextView studentMajorTV = holder.reference.findViewById(R.id.majorTV);
//        studentMajor = jobsModel.jobsArray.get(position).major;
//        studentMajorTV.setText(jobsModel.jobsArray.get(position).major);
//
//        final TextView qualificationTV = holder.reference.findViewById(R.id.qualiTV);
//        qualification = jobsModel.jobsArray.get(position).qualifications;
//        qualificationTV.setText(jobsModel.jobsArray.get(position).qualifications);
//
//        final TextView deadlineTV = holder.reference.findViewById(R.id.deadlineTV);
//        deadline = jobsModel.jobsArray.get(position).deadline;
//        deadlineTV.setText(jobsModel.jobsArray.get(position).deadline);
//
//        final DatabaseReference reference;
//        reference = FirebaseDatabase.getInstance().getReference().child("MYJOBS");
//
//        Button applyBTN = holder.reference.findViewById(R.id.applyBTN);
//        applyBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jobName = jobNameTV.getText().toString();
//                jobTitle = jobTitleTV.getText().toString();
//                jobDesc = jobDescTV.getText().toString();
//                studentMajor = studentMajorTV.getText().toString();
//                qualification = qualificationTV.getText().toString();
//                deadline = deadlineTV.getText().toString();
//                JobsModel.JobsModelInfo job = new JobsModel.JobsModelInfo(jobName, jobTitle, jobDesc, studentMajor, qualification, deadline);
//                reference.push().setValue(job);
//
//                Toast.makeText(v.getContext(), "Your Application was successfully submitted", Toast.LENGTH_LONG).show();
//
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return Restaurant.re.size();
//    }
//
//    public interface MyOnClick {
//        public void itemClick(int position);
//    }
//
//
//}