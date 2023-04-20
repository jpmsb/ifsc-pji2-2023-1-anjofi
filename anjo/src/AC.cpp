#include "AC.h"

AC::AC(bool areadyOn, int pin) : sensor(pin){
}

void AC::getValues(){
    previousTemperature = sensor.getCurrent();
    sensor.measure();
    currentTemperature = sensor.getCurrent();
}

bool AC::getStatus(){
    return currentTemperature < previousTemperature;
}

float AC::getCurrentTemperature(){
    return currentTemperature;
}

float AC::getPreviousTemperature(){
    return previousTemperature;
}
