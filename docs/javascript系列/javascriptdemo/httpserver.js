
var http = require('http'),fs = require('fs');

var server = http.createServer(function(req,res){

    if('GET'==req.method && '/images' ==  req.url.substr(0,7) && '.jpg'== req.url.substr(-4))
    {
        console.log("=====1");
        console.log('dirname = '+ __dirname);
        fs.stat(__dirname + req.url,function(err,stat){
            if(err || !stat.isFile()){
                res.writeHead(404);
                res.end('Not found');
                console.log("404,not found");
                return;
            }
            else{
                console.log('ok,');
                serve(__dirname + req.url,'application/jpg');
            }
        });
    }
    else if('GET'== req.method && '/' == req.url){
        console.log('dirname = '+ __dirname);
        console.log("=====2");
        serve(__dirname + '/index.html','text/html');
    }
    else{
        console.log("=====3");
        console.log('method = '+ req.method);
        console.log('dirname = '+ __dirname);
        res.writeHead(404);
        res.end('Not found');
    }
    function serve(path,type){
        res.writeHead(200,{'Content-Type':type});
        fs.createReadStream(path).pipe(res);
    }

});


server.listen(3000);

