export class Address {
  id?: number;
  streetNumber?: string;
  streetName?: string ;
  city?: string;
  state?: string;
  zipCode?: string;

  constructor(id?: number, streetNumber?: string, streetName?: string, city?: string, state?: string, zipCode?: string) {
    this.id = id;
    this.streetNumber = streetNumber;
    this.streetName = streetName;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
  }

  toString(): string {
    return `Address{id=${this.id}, streetNumber='${this.streetNumber}', streetName='${this.streetName}', city='${this.city}', state='${this.state}', zipCode='${this.zipCode}'}`;
  }
}
