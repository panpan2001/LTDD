package com.midterm.fashionrecommend.Callback;

import com.midterm.fashionrecommend.Model.CommentModel;

import java.util.List;

public interface ICommentCallbackListener {
    void onCommentLoadSuccess(List<CommentModel> commentModels);
    void onCommentLoadFailed(String message);
}
