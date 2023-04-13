#ifndef _TEMPERATURA_H_
#define _TEMPERATURA_H_

#include <Arduino.h>
#include "DHT.h"

class Temperatura {
    public:
        Temperatura(int pin = 12);

        void measure();

        void measureBaseValue();

        int getCurrent();

        int getBaseValue();

        ~Temperatura() {};

    private:
        int baseValue = 0;
        float previousValue = 0;
        float currentValue = 0;
        DHT dht;
};

#endif
