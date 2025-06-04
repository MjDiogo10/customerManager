import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {
  PoAccordionModule,
  PoButtonModule,
  PoDynamicFormComponent,
  PoDynamicFormField,
  PoDynamicModule,
  PoInfoModule,
  PoListViewAction,
  PoListViewModule
} from '@po-ui/ng-components';
import Customer from '../../../core/customer/entities/customer';
import {CustomerService} from '../../../core/customer/service/customer.service';
import {Router} from '@angular/router';
import Address from '../../../core/customer/entities/address';
import Phone from '../../../core/customer/entities/phone';

@Component({
  selector: 'app-create-customer',
  imports: [
    PoDynamicModule,
    PoButtonModule,
    PoListViewModule,
    PoInfoModule,
    PoAccordionModule
  ],
  templateUrl: './create-customer.component.html',
  standalone: true,
  styleUrl: './create-customer.component.scss'
})
export class CreateCustomerComponent implements OnInit {

  @ViewChild('dynamicFormCustomer', { static: true }) dynamicFormCustomer!: PoDynamicFormComponent;
  @ViewChild('dynamicFormAddress', { static: true }) dynamicFormAddress!: PoDynamicFormComponent;
  @ViewChild('dynamicFormPhone', { static: true }) dynamicFormPhone!: PoDynamicFormComponent;

  service: CustomerService = inject(CustomerService);
  route: Router = inject(Router);

  customer: Customer = new Customer();
  fieldsCustomer: PoDynamicFormField[] = [];

  address: Address = new Address();
  addresses: Address[] = [];
  fieldsAddress: PoDynamicFormField[] = [];
  actionsAddress: PoListViewAction[] = [];

  phone: Phone = new Phone();
  phones: Phone[] = [];
  fieldsPhone: PoDynamicFormField[] = [];
  actionsPhone: PoListViewAction[] = [];

  ngOnInit(): void {
    this.configureFieldsCustomer();
    this.configureFieldsAddress();
    this.configureActionsAddress();
    this.configureFieldsPhone();
    this.configureActionsPhone();
  }

  configureFieldsCustomer() {
    this.fieldsCustomer = [
      {
        container: 'Dados Pessoais',
        property: 'name',
        label: 'Nome',
        required: true,
        minLength: 10,
        maxLength: 50,
        gridColumns: 6,
        gridSmColumns: 12,
        order: 1,
      },
      {
        property: 'cpf',
        label: 'CPF',
        required: true,
        mask: '999.999.999-99',
        gridColumns: 6,
        gridSmColumns: 12,
        order: 2,
      },
    ];
  }

  configureFieldsAddress() {
    this.fieldsAddress = [
      {
        property: 'street',
        container: 'Endereço',
        label: 'Rua',
        gridColumns: 6,
        required: true,
      },
      {
        property: 'number',
        label: 'Número',
        gridColumns: 6,
        required: true,
      },
      {
        property: 'complement',
        label: 'Complemento',
        gridColumns: 12,
        required: true,
      },
      {
        property: 'neighborhood',
        label: 'Bairro',
        gridColumns: 6,
        required: true,
      },
      {
        property: 'city',
        label: 'Cidade',
        gridColumns: 6,
        required: true,
      },
      {
        property: 'state',
        label: 'Estado',
        gridColumns: 6,
        required: true,
      },
      {
        property: 'country',
        label: 'País',
        gridColumns: 6,
        required: true,
      },
      {
        property: 'postalCode',
        label: 'CEP',
        gridColumns: 6,
        mask: '99999-999',
        required: true
      }
    ];
  }

  configureActionsAddress() {
    this.actionsAddress = [
      {
        label: 'Editar',
        action: this.editAddress.bind(this),
        icon: 'an an-pencil'
      },
      {
        label: 'Remover',
        action: this.removeAddress.bind(this),
        type: 'danger',
        icon: 'an an-trash'
      }
    ];
  }

  configureFieldsPhone() {
    this.fieldsPhone = [
      {
        property: 'phone',
        container: 'Contato',
        label: 'Telefone',
        gridColumns: 12,
        mask: '+99 (99) 99999-9999',
        required: true,
      }
    ];
  }

  configureActionsPhone() {
    this.actionsPhone = [
      {
        label: 'Editar',
        action: this.editPhone.bind(this),
        icon: 'an an-pencil'
      },
      {
        label: 'Remover',
        action: this.removePhone.bind(this),
        type: 'danger',
        icon: 'an an-trash'
      }
    ];
  }

  goToListing() {
    this.route.navigate(['customers']);
  }

  addCustomer() {
    if (!this.dynamicFormCustomer?.form?.valid) return;

    this.customer.phones = this.phones.map(phone => phone.phone);
    this.customer.addresses = this.addresses;

    this.service.create(this.customer).subscribe(() => {
      this.goToListing();
    });
  }

  addAddress() {
    if (!this.dynamicFormAddress?.form?.valid) return;

    this.addresses.push(this.address);
    this.address = new Address();
  }

  editAddress(item: Address) {
    this.address = this.addresses.find((address) =>
      address.street === item.street
      && address.number === item.number
    )!;

    this.removeAddress(item);
  }

  removeAddress(item: Address) {
    this.addresses = this.addresses.filter((address) => {
      return address.street !== item.street
        && address.number !== item.number;
    });
  }

  addPhone() {
    if (!this.dynamicFormPhone?.form?.valid) return;

    this.phones.push(this.phone);
    this.phone = new Phone();
  }

  editPhone(item: Phone) {
    this.phone = this.phones.find((phone) =>
      phone.phone === item.phone
    )!;

    this.removePhone(item);
  }

  removePhone(item: Phone) {
    this.phones = this.phones.filter((phone) => {
      return phone.phone !== item.phone;
    });
  }
}
