import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { CustomerService} from '../service/customer.service';
import {Customer} from "../model/customer";


@Component({
  selector: 'app-get-customer-by-id',
  templateUrl: './get-customer-by-id.component.html',
  styleUrl: './get-customer-by-id.component.css'
})
export class GetCustomerByIdComponent {
  customers?: Customer;
  id?: number;

  constructor(private customerService: CustomerService) {
  }

  searchCustomer(): void {
    if (this.id !== undefined && this.id !== null) {
      this.ngOnInit(this.id);
    } else {
      console.error('Invalid ID', this.id);
    }
  }
  ngOnInit(id: number): void {
    this.customerService.getCustomerById(id).subscribe(
      (data: any) => {
        console.log(data);
        if (data.body && data.body.data) {
          this.customers = data.body.data;
        } else {
          console.error('Unexpected data structure', data);
        }
      },
      (error: any) => {
        console.error('Error getting customer', error)
      }
    )
  }
}
