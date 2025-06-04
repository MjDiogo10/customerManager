import {Component, inject, OnInit} from '@angular/core';
import {PoButtonModule, PoTableColumn, PoTableModule} from '@po-ui/ng-components';
import {CustomerService} from '../../../core/customer/service/customer.service';
import CustomerViewList from '../../../core/customer/entities/customerViewList';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-customer',
  imports: [
    PoTableModule,
    PoButtonModule
  ],
  templateUrl: './list-customer.component.html',
  standalone: true,
  styleUrl: './list-customer.component.scss'
})
export class ListCustomerComponent implements OnInit {

  service: CustomerService = inject(CustomerService);
  route: Router = inject(Router);

  columns: PoTableColumn[] = [];
  items: CustomerViewList[] = [];

  ngOnInit(): void {
    this.configureColumns();
    this.list();
  }

  configureColumns() {
    this.columns = [
      {
        property: 'name',
        label: 'Nome'
      },
      {
        property: 'cpf',
        label: 'cpf'
      },
      {
        property: 'actions',
        label: 'Ações',
        type: 'icon',
        sortable: false,
        icons: [
          {
            action: this.updateCustomer.bind(this),
            icon: 'po-icon-edit',
            tooltip: 'Editar',
            value: 'update'
          },
          {
            action: this.deleteCustomer.bind(this),
            icon: 'po-icon-delete',
            tooltip: 'Excluir',
            value: 'delete'
          }
        ]
      }
    ];
  }

  list() {
    this.service.list().subscribe(response => {
      this.items = response;

      this.items = this.items.map(item => {
        item.actions = ['update', 'delete'];
        return item;
      });
    });
  }

  addCustomer() {
    this.route.navigate(['customers/create']);
  }

  updateCustomer(customer: CustomerViewList) {
    this.route.navigate(['customers/update/', customer.id]);
  }

  deleteCustomer(customer: CustomerViewList) {
    this.service.deleteById(customer.id).subscribe(() => {
      this.list();
    });
  }
}
