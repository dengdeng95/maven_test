var loadUrl = function(url) {
    $("#content").empty();
    $.ajax({
        type: "get",
        url: url,
        cache: false,
        dataType: "html",
        success: function(data) {
            $("#content").html(data);
        },
        error: function(e) {
            $("#content").html("<div class='row'><h1>此路径不存在：" + url.substring(url.indexOf("u=") + 2) + "</h1></div>");
        }
    });
};

var loadUrl2Div = function(url, divId) {
    $("#" + divId).empty();
    $.ajax({
        type: "get",
        url: url,
        cache: false,
        dataType: "html",
        success: function(data) {
            $("#" + divId).html(data);
        },
        error: function(e) {
            $("#" + divId).html("<div class='row'><h1>此路径不存在：" + url.substring(url.indexOf("u=") + 2) + "</h1></div>");
        }
    });
};

$(document).ajaxSuccess(function(event, xhr, options) {
    if (xhr.getResponseHeader("nologin") == "true") {
        var data = JSON.parse(xhr.responseText.replace(/\\/g, "\\\\"));
        layer.msg("登录超时", {
            time: 2e3,
            ico: 2
        }, function() {
            window.location.href = data.loginUrl;
        });
    }
});

var permissions = function() {
    var HRY_permissions_array = HRY_permissions.split(",");
    var elements = $("[permissions]");
    if (elements.length > 0) {
        for (var i = 0; i < elements.length; i++) {
            var obj = $(elements[i]);
            var key = obj.attr("permissions");
            if (HRY_permissions_array.indexOf(key) == -1) {
                obj.remove();
            }
        }
    }
};

define("base/utils/base", [], function(require, exports, module) {
    module.exports = {
        loadUrl: loadUrl
    };
});

var guid = function() {
    function S4() {
        return ((1 + Math.random()) * 65536 | 0).toString(16).substring(1);
    }
    return S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4();
};

var pageTimer = {};

var clearPageTimer = function() {
    try {
        for (var each in pageTimer) {
            window.clearInterval(pageTimer[each]);
        }
    } catch (e) {}
};