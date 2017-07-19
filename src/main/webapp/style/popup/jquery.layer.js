/*!
 * The jQuery Plugin of layer
 * http://kyo4311.github.io/jquery.layer/
 * Copyright guosheng
 * Released under the MIT license
 */

(function() {
    var maskDiv = $('<div class="jquery-layer-mask"></div>');
    var tipDiv = $('<div class="jquery-layer-tip zoomIn"></div>');
    var timer;

    function mask() {
        return maskDiv.appendTo('body').show();
    }

    function hideMask() {
        return maskDiv.hide();
    }

    function _bindEvt(obj) {
        obj.find('.jquery-layer-close-evt').on('click', function() {
            removeDiv(obj);
        });

        $(window).on('resize', function() {
            _setDivCenter(obj);
        });
    }

    //移除
    function removeDiv(div) {
        div.remove();
        hideMask();
    }

    //居中
    function _setDivCenter(obj) {
        obj.css({
            marginLeft: -obj.outerWidth() / 2,
            marginTop: -obj.outerHeight() / 2,
        });
    }

    //组装div
    function assemble(div, callback) {
        div = $(div).appendTo('body');
        div.find('.jquery-layer-close').focus();
        callback.call(null, div);
        mask();
        _setDivCenter(div);
        _bindEvt(div);
        return div;
    }

    //包装div
    function wrapDiv(head, body, foot) {
        var div = '<div class="#{z} zoomIn" style="width:#{width}; height:#{height};"><form>';
        div += head;
        div += body;
        div += foot;
        div += '</form></div>';
        return div;
    }

    //返回头部html
    function getHeader() {
        return '<div class="#{z}-title noselect"><span class="#{z}-h2">#{title}</span><a href="#{j}" class="#{z}-close #{z}-close-evt"></a></div>';
    }

    //获得底部html
    function getFooter(btns) {
        var footer = '<div class="#{z}-bar #{z}-noselect #{z}-zoom">';
        $(btns).each(function(index, item) {
            footer += '<a href="#{j}" class="#{z}-btn-' + index + ' #{z}-btn #{z}-ml10 #{z}-fr">' + item + '</a>';
        });
        footer += '<div>';
        return footer;
    }

    //获得主体html
    function getContent() {
        return '<div class="#{z}-nav">#{content}</div>';
    }

    //合并前缀
    function extendPrefix(opt) {
        return $.extend(opt, {
            j: 'javascript:;',
            z: 'jquery-layer'
        });
    }


    function __tip(msg) {
        tipDiv.text(msg).appendTo('body').show();
        _setDivCenter(tipDiv);
        clearTimeout(timer);
        timer = setTimeout(function() {
            tipDiv.fadeOut();
        }, 3000);
        return tipDiv;
    }

    function __confirm(msg, callback) {
        return __box({
            title: '确认框',
            content: msg,
            btns: ['确定', '取消'],
            callback: function(listen) {
                listen('取消', function(close, serialize) {
                    close();
                });
                listen('确定', function(close, serialize) {
                    callback();
                    close();
                });
            }
        }).find('.jquery-layer-btn-0').focus().addClass('jquery-layer-btn-blue').end();
    }

    function __alert(msg) {
        return __box({
            title: '确认框',
            content: msg,
            btns: ['确定'],
            callback: function(listen) {
                listen('确定', function(close, serialize) {
                    close();
                });
            }
        }).find('.jquery-layer-btn-0').addClass('jquery-layer-btn-blue').end();
    }

    function __iframe(opt) {
        opt.content = '<iframe src="' + opt.url + '" class="layer-loading" frameborder="no" width="100%" border="0">';
        var box = __box(opt).find('.jquery-layer-btn-0').addClass('jquery-layer-btn-blue').end();
        box.find('iframe').height(box.outerHeight() - 73);

        return box;
    }


    function __form(opt) {
        var box;
        opt.btns = ['提交'];
        opt.callback = function(listen) {
            listen('提交', function(close, serialize, serializeArray) {
                if (opt.beforeSubmit) {
                    var res = opt.beforeSubmit.call(box, serialize, serializeArray);
                    if (res === false) {
                        return;
                    }
                }
                $.post(opt.postUrl, serialize, function(data) {
                    if (opt.postSuccess) {
                        opt.postSuccess.call(null, close, data);
                    }
                });
            });
        };
        //取数据
        if (opt.getUrl) {
            $.get(opt.getUrl, function(res) {
                opt.content = _template(opt.content, res);
                box = __box(opt).find('.jquery-layer-btn-0').focus().addClass('jquery-layer-btn-blue').end();
            });
        } else {
            opt.content = _template(opt.content, {});
            box = __box(opt).find('.jquery-layer-btn-0').focus().addClass('jquery-layer-btn-blue').end();
        }

        return box;
    }

    function __box(opt) {
        var funcs = {};
        var html = wrapDiv(getHeader(), getContent(), getFooter(opt.btns));

        html = _template(html, extendPrefix(opt));
        html = assemble(html, function(box) {
            var form = box.find('form');
            box.find('.jquery-layer-btn').on('click', function() {
                var func = funcs[$(this).text()];
                if (typeof func === 'function') {
                    func.call(box, function() {
                        removeDiv(box);
                    }, form.serialize(), form.serializeArray());
                }
            });
        });

        if (opt.callback) {
            opt.callback.call(this, function(a, b) {
                funcs[a] = b;
            });
        }
        return html;
    }

    function _template(template, data) {
        return template.replace(/#\{([\s\S]+?)\}/g, function(a, b) {
            return data[b] || '';
        });
    }

    $.extend({
        layer: {
            mask: mask,
            hideMask: hideMask,
            tip: __tip,
            box: __box,
            confirm: __confirm,
            alert: __alert,
            iframe: __iframe,
            form: __form
        }
    });
}());