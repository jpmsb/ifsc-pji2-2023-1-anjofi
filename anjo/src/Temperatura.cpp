#include <Temperatura.h>

Temperatura::Temperatura(int pin) : dht(pin, DHT11){
    dht.begin();
}

void Temperatura::measure(){
    previousValue = currentValue;
    currentValue = dht.readTemperature() * 0.79;
}

void Temperatura::measureBaseValue(){
    measure();
    baseValue = currentValue;
}

float Temperatura::getCurrent(){
    return currentValue;
}

float Temperatura::getBaseValue(){
    return baseValue;
}
