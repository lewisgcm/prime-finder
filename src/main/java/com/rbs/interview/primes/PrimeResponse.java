package com.rbs.interview.primes;

import com.fasterxml.jackson.annotation.JsonGetter;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="Primes")
public final class PrimeResponse {
    private int initial;
    private int[] primes;

    private PrimeResponse() {}

    public PrimeResponse(int initial, int[] primes) {
        this.initial = initial;
        this.primes = primes;
    }

    @JsonGetter("Initial")
    @XmlAttribute(name="Initial")
    public int getInitial() {
        return initial;
    }

    @JsonGetter("Primes")
    @XmlElement(name="Prime")
    public int[] getPrimes() {
        return primes;
    }
}
