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
}