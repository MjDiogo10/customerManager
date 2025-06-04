import Address from './address';

export default class Customer {
  id!: number | null;
  name!: string;
  cpf!: string;
  phones!: string[];
  addresses!: Address[];
}
