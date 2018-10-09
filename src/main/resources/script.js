var myCompute = function(a, b) {
  print('Hi there from Javascript');
  var peso = 2;
  var ramdom = Math.random();
  var result = peso * ramdom * (a+b) ;
  return result | 0;
};

var myComputeArray = function(valuesArray) {
    print('Hi there from Javascript myComputeArray function');
    var peso = 2;
    var ramdom = Math.random();
    var par1 = valuesArray[0];
    var par2 = valuesArray[1];
    var result = peso * ramdom * (par1+par2) ;
    return result | 0;
};

var myComputeMap = function(valuesMap) {
    var par1 = valuesMap["par1"];
    var par2 = valuesMap["par2"];
    var ramdom = Math.random();
    var peso = 2;
    var result = peso * ramdom * (par1+par2) ;
    return result | 0;
};


var fun2 = function(mapValues) {
    print("JS Class Definition: " + Object.prototype.toString.call(mapValues));
    var par1 = mapValues.get("par1");
    var par2 = mapValues.get("par2");
    var ramdom = Math.random();
    for each (var e in mapValues.keySet()) print(e);
    var peso = 2;
    var result = peso * ramdom * (par1+par2) ;
    return result | 0;

};