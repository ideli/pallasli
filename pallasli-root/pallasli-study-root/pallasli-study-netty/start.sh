#!/bin/sh

#启动方法
start(){
    java niocommunicate.JAVANIOServer >server.log&
    echo $!>/var/tmp/pid.log
    pid=$(cat /var/tmp/pid.log)
    echo JAVANIOServer[$pid]启动成功
}

stop(){
      pid=$(cat /var/tmp/pid.log)
      kill -9 $pid
      echo 关闭[$pid]JAVANIOServer成功
    
}
case "$1" in
start)
    start
;;
stop)
    stop
;;
restart)
    stop
    start
;;
*)
printf 'Usage: %s{start|stop|restart}\n'"$prog"
exit 1
;;
esac