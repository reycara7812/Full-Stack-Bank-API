import { Address } from './address';
export class Customer {
  id?: number;
  firstName?: string;
  lastName?: string;
  addresses?: Address[];

  constructor(id?: number, firstName?: string, lastName?: string, addresses?: Address[]) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.addresses = addresses;
  }
}
