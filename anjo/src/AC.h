#ifndef _AC_H_
#define _AC_H_

#include "TemperatureHumidity.h"

class AC {
    public:
       AC(bool alreadyOn = false, int pin = 12);

       void getValues();

       bool getStatus();

       bool getAlreadyOn();

       float getCurrentTemperature();

       float getPreviousTemperature();

       float getCurrentHumidity();

    private:
       float threshold = 1;
       float initialTemperature = 0;
       float currentTemperature;
       float previousTemperature;
       float currentHumidity;
       bool alreadyOn;
       TemperatureHumidity sensor;
}; 

#endif
