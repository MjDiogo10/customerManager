import {catchError, Observable, throwError} from 'rxjs';
import Customer from '../entities/customer';
import {Injectable, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import CustomerViewList from '../entities/customerViewList';
import {PoNotificationService} from '@po-ui/ng-components';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  http:HttpClient =  inject(HttpClient);
  poNotificationService: PoNotificationService = inject(PoNotificationService);

  formatarErros(error: any) {
    this.poNotificationService.error(error.error);
    return throwError(() => error);
  }

  public getUrlContext(): string {
    return `http://localhost:8080/api`;
  }

  create(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.getUrlContext()}/customer`, customer).pipe(
      catchError(error => this.formatarErros(error))
    );
  }

  update(customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.getUrlContext()}/customer`, customer).pipe(
      catchError(error => this.formatarErros(error))
    );
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.getUrlContext()}/customer/${id}`).pipe(
      catchError(error => this.formatarErros(error))
    );
  }

  list(): Observable<CustomerViewList[]> {
    return this.http.get<CustomerViewList[]>(`${this.getUrlContext()}/customer`).pipe(
      catchError(error => this.formatarErros(error))
    );
  }

  getById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.getUrlContext()}/customer/${id}`).pipe(
      catchError(error => this.formatarErros(error))
    );
  }
}
