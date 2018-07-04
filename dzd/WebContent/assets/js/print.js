function doPrint() {
    bdhtml = window.document.body.innerHTML;
    sprnstr = "<!--startprint-->"; //开始打印标识字符串
    eprnstr = "<!--endprint-->"; //结束打印标识字符串
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17); //从开始打印标识之后的内容
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容
    window.document.body.innerHTML = prnhtml; //把需要打印的指定内容赋给body.innerHTML
    window.print(); //调用浏览器的打印功能打印指定区域
    window.document.body.innerHTML = bdhtml;//重新给页面内容赋值；
    window.location.reload();
}