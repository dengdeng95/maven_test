define(function(require, exports, module) {
	this.md5 = require("base/utils/md5");
	this.md5 = require("base/utils/base");
	
	module.exports = {
		login : function(){
			$("#loginBtn").on("click", function() {
                var username = $("#username").val();
                var password = $("#password").val();
                if (!username) {
                    layer.msg("手机号不能为空", {
                        icon: 2
                    });
                    return;
                }
                if (!password) {
                    layer.msg("密码不能为空", {
                        icon: 2
                    });
                    return;
                }
                $.ajax({
                    type: "post",
                    url: _ctx + "/loginService",
                    data: {
                        username: username,
                        password: md5.md5(password)
                    },
                    cache: false,
                    dataType: "json",
                    success: function(data) {
                        if (data) {
                            if (data.success) {
                                window.location.href = _ctx + "/user/center";
                            } else {
                                layer.msg(data.msg, {
                                    icon: 2
                                });
                            }
                        } else {
                            layer.msg("登录失败", {
                                icon: 2
                            });
                        }
                    },
                    error: function(e) {}
                });
            });
		}
	}
})