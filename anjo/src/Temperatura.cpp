#include <Temperatura.h>

Temperatura::Temperatura(int pin) : dht(pin, DHT11){
    dht.begin();
}

void Temperatura::measure(){
    previousValue = currentValue;
    currentValue = dht.readTemperature();
}

void Temperatura::measureBaseValue(){
    measure();
    baseValue = currentValue;
}

int Temperatura::getCurrent(){
    return currentValue;
}

int Temperatura::getBaseValue(){
    return baseValue;
}
