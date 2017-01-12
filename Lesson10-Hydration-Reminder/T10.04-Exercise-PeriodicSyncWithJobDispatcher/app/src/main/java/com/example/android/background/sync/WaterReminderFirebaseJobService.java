/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.sync;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class WaterReminderFirebaseJobService extends JobService {
    // COMPLETE (3) WaterReminderFirebaseJobService should extend from JobService
    private AsyncTask mBackgroundTask;

    // COMPLETE (4) Override onStartJob
    @Override
    public boolean onStartJob(final JobParameters params) {
        // COMPLETE (5) By default, jobs are executed on the main thread, so make an anonymous class extending
        //  AsyncTask called mBackgroundTask.
        mBackgroundTask = new AsyncTask() {
            // COMPLETE (6) Override doInBackground
            @Override
            protected Object doInBackground(Object[] params) {
                // COMPLETE (7) Use ReminderTasks to execute the new charging reminder task you made, use
                // this service as the context (WaterReminderFirebaseJobService.this) and return null
                // when finished.
                ReminderTasks.executeTask(WaterReminderFirebaseJobService.this, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }
            // COMPLETE (8) Override onPostExecute and called jobFinished. Pass the job parameters
            // and false to jobFinished. This will inform the JobManager that your job is done
            // and that you do not want to reschedule the job.
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                jobFinished(params, false);
            }
        };



        // COMPLETE (9) Execute the AsyncTask
        mBackgroundTask.execute();
        // COMPLETE (10) Return true
        return true;
    }

    // COMPLETE (11) Override onStopJob
    @Override
    public boolean onStopJob(JobParameters params) {
        // COMPLETE (12) If mBackgroundTask is valid, cancel it
        if(mBackgroundTask != null)
            mBackgroundTask.cancel(true);
        // COMPLETE (13) Return true to signify the job should be retried
        return true;
    }
}
