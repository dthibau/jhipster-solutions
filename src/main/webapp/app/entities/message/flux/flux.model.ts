import { BaseEntity, User } from './../../../shared';

export class Flux implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public children?: BaseEntity[],
    ) {
    }
}
