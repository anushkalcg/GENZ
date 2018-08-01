export const test = (item) => {
    console.log(item);
    return {
        type: 'add',
        item
    }
};