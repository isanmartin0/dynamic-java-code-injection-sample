var myCompute = function(a, b) {
  print('Hi there from Javascript');
  var peso = 2;
  var ramdom = Math.random();
  var result = peso * ramdom * (a+b) ;
  return result | 0;
};