package com.example.chatview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.chatview.R;
import com.example.chatview.adapter.MsgAdapter;
import com.example.chatview.data.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> mMsgList = new ArrayList<>();

    private EditText inputText;

    private RecyclerView mRecyclerView;

    private MsgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据
        initMsg();
        inputText = findViewById(R.id.input_text);
        mRecyclerView = findViewById(R.id.meg_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MsgAdapter(mMsgList);
        mRecyclerView.setAdapter(mAdapter);

        //将RecyclerView定位到最后一行
        mRecyclerView.scrollToPosition(mMsgList.size() - 1);


    }

    private void initMsg() {
        Msg msg1 = new Msg("Hello guys",Msg.TYPE_RECEIVED);
        mMsgList.add(msg1);
        Msg msg2 = new Msg("Hi,who is that?",Msg.TYPE_SENT);
        mMsgList.add(msg2);
        Msg msg3 = new Msg("This is Tom,Nice to meet you.",Msg.TYPE_RECEIVED);
        mMsgList.add(msg3);

        mMsgList.add(new Msg("Oh.me too",Msg.TYPE_SENT));
        mMsgList.add(new Msg("emmm,can you tell me your name?",Msg.TYPE_RECEIVED));
        mMsgList.add(new Msg("Oh,sorry,i just forgot to introduce myself,i am qricis",Msg.TYPE_SENT));
        mMsgList.add(new Msg("that's ok,what a beautiful name and i like it",Msg.TYPE_RECEIVED));
        mMsgList.add(new Msg("Oh,thanks for your like",Msg.TYPE_SENT));
        mMsgList.add(new Msg("By the way,how about go lunch together?",Msg.TYPE_RECEIVED));
        mMsgList.add(new Msg("Sure,i am just so hungry now",Msg.TYPE_SENT));

    }

    public void sendMsg(View view) {
        String content = inputText.getText().toString();

        if(!"".equals(content)) {
            Msg msg = new Msg(content,Msg.TYPE_SENT);
            mMsgList.add(msg);

            //有新消息时，刷新RecyclerView中的显示
            mAdapter.notifyItemInserted(mMsgList.size() - 1);

            //将RecyclerView定位到最后一行
            mRecyclerView.scrollToPosition(mMsgList.size() - 1);

            //清空输入框中的内容
            inputText.setText("");

        }
    }

}