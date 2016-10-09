/**
 * Created by Simple_love on 2016/3/30.

 */
$(function() {
        var Accordion = function(el, multiple) {
                this.el = el || {};
                this.multiple = multiple || false;

                // Variables privadas
                var links = this.el.find('.link');
                // Evento
                links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
        }

        Accordion.prototype.dropdown = function(e) {
                var $el = e.data.el;
                $this = $(this),
                    $next = $this.next();

                $next.slideToggle();
                $this.parent().toggleClass('open');
                if($this.find("span[class='glyphicon glyphicon-chevron-down']").length == 0)
                        $this.find("span[class='glyphicon glyphicon-chevron-up']").attr("class","glyphicon glyphicon-chevron-down");
                else
                        $this.find("span[class='glyphicon glyphicon-chevron-down']").attr("class","glyphicon glyphicon-chevron-up");
                if (!e.data.multiple) {
                        $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
                        /*$this.find("span[class='glyphicon glyphicon-chevron-up']").attr("class","glyphicon glyphicon-chevron-down")*/
                };
        }

        var accordion = new Accordion($('#accordion'), false);
});