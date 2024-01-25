import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { CustomerService} from '../service/customer.service';
import {Customer} from "../model/customer";
import { Observable } from 'rxjs';
@Component({
  selector: 'app-get-all-customer',
  templateUrl: './get-all-customer.component.html',
  styleUrl: './get-all-customer.component.css'
})
export class GetAllCustomerComponent implements OnInit{
  customers?: Customer[] ;
constructor(private customerService: CustomerService) { }


  ngOnInit(): void {
    this.customerService.getAllCustomers().subscribe(
      (data: any) => {
        console.log(data);
        if (Array.isArray(data.body.data)) {
          this.customers = data.body.data;
        } else {
          console.error('Unexpected data structure', data);
        }
      },
      (error : any) => {
        console.error('Error getting customer', error)
      }
    )
  }



}
